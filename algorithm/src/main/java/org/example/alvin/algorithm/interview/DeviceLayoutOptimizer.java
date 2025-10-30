package org.example.alvin.algorithm.interview;

import java.util.*;

/**
 * 运行环境：JDK21.0.7
 */

/** 辅助类：表示一个设备连接 */
class DeviceConnection {
    String dev1;
    String dev2;

    public DeviceConnection(String dev1, String dev2) {
        this.dev1 = dev1;
        this.dev2 = dev2;
    }
}

/**
 * 辅助类：表示一个位置连接
 */
class LocationConnection {
    String loc1;
    String loc2;
    int distance;

    public LocationConnection(String loc1, String loc2, int distance) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.distance = distance;
    }
}

/**
 * 辅助类：用于返回最终结果
 */
class LayoutResult {
    Map<String, String> layout;
    long totalDistance;
    boolean found;

    public LayoutResult() {
        this.layout = new HashMap<>();
        this.totalDistance = Long.MAX_VALUE;
        this.found = false;
    }

    @Override
    public String toString() {
        if (!found) {
            return "未找到满足条件的布局";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("找到最优布局：\n");
        for (Map.Entry<String, String> entry : layout.entrySet()) {
            sb.append(String.format("  设备 %s -> 位置 %s\n", entry.getKey(), entry.getValue()));
        }
        sb.append(String.format("最小总距离: %d\n", totalDistance));
        return sb.toString();
    }
}

/**
 * 设备布局优化器
 */
public class DeviceLayoutOptimizer {

    // 定义一个大的数表示无穷大，用于Floyd-Warshall算法
    // 使用 Long.MAX_VALUE / 3 来防止加法溢出
    private static final long INFINITY = Long.MAX_VALUE / 3;

    private List<String> devices;
    private List<String> locations;
    private List<DeviceConnection> deviceConnections;
    private Map<String, Integer> deviceIndexMap;
    private Map<String, Integer> locationIndexMap;
    private long[][] locationDistMatrix; // 位置间的最短路径距离矩阵
    private int m; // 设备数量
    private int n; // 位置数量

    private LayoutResult bestResult;

    /**
     * 主入口方法
     */
    public LayoutResult findOptimalLayout(List<String> devices,
                                          List<String> locations,
                                          List<DeviceConnection> deviceConnections,
                                          List<LocationConnection> locationConnections) {
        this.devices = devices;
        this.locations = locations;
        this.deviceConnections = deviceConnections;
        this.m = devices.size();
        this.n = locations.size();
        this.bestResult = new LayoutResult();

        if (m > n) {
            System.out.println("错误：设备数量大于位置数量，无法安放。");
            return bestResult;
        }

        // 建立名称到索引的映射，方便后续处理
        this.deviceIndexMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            deviceIndexMap.put(devices.get(i), i);
        }
        this.locationIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            locationIndexMap.put(locations.get(i), i);
        }

        // 步骤 1: 预处理位置图，计算所有位置点对之间的最短路径
        buildLocationDistanceMatrix(locationConnections);

        // 步骤 2: 生成所有布局可能性（排列）
        // currentPlacement[i] = j 表示：第 i 个设备 放在了 第 j 个位置
        int[] currentPlacement = new int[m];
        boolean[] locationUsed = new boolean[n];
        generatePlacementsRecursive(0, currentPlacement, locationUsed);

        return bestResult;
    }

    /**
     * 步骤 1: 使用 Floyd-Warshall 算法构建全点对最短路径矩阵
     */
    private void buildLocationDistanceMatrix(List<LocationConnection> locationConnections) {
        locationDistMatrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(locationDistMatrix[i], INFINITY);
            locationDistMatrix[i][i] = 0; // 自己到自己的距离为0
        }

        // 初始化直接连接的距离
        for (LocationConnection conn : locationConnections) {
            int idx1 = locationIndexMap.get(conn.loc1);
            int idx2 = locationIndexMap.get(conn.loc2);
            // 假设是无向图
            locationDistMatrix[idx1][idx2] = conn.distance;
            locationDistMatrix[idx2][idx1] = conn.distance;
        }

        // Floyd-Warshall 核心算法
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (locationDistMatrix[i][k] + locationDistMatrix[k][j] < locationDistMatrix[i][j]) {
                        locationDistMatrix[i][j] = locationDistMatrix[i][k] + locationDistMatrix[k][j];
                    }
                }
            }
        }
    }

    /**
     * 步骤 2: 递归回溯法，生成所有设备到位置的排列
     *
     * @param deviceIdx      当前正在安放的设备索引
     * @param currentPlacement 记录当前布局的数组
     * @param locationUsed     记录哪些位置已被占用
     */
    private void generatePlacementsRecursive(int deviceIdx, int[] currentPlacement, boolean[] locationUsed) {
        // --- 递归基线条件：所有设备都已安放 ---
        // 这是"有哪些可能性"中的一种
        if (deviceIdx == m) {
            // 步骤 3 & 4: 验证和计算当前布局的总距离，并更新最优解
            checkAndCalculateLayout(currentPlacement);
            return;
        }

        // --- 递归步骤 ---
        // 尝试将第 deviceIdx 个设备 放到所有可用的位置
        for (int locIdx = 0; locIdx < n; locIdx++) {
            if (!locationUsed[locIdx]) {
                // 1. 做出选择
                currentPlacement[deviceIdx] = locIdx; // 将设备i 放在 位置j
                locationUsed[locIdx] = true;          // 标记位置j 已被占用

                // 2. 深入下一层
                generatePlacementsRecursive(deviceIdx + 1, currentPlacement, locationUsed);

                // 3. 撤销选择（回溯）
                locationUsed[locIdx] = false;         // 标记位置j 未被占用
            }
        }
    }

    /**
     * 步骤 3 & 4: 检查当前布局是否满足连接性，并计算总距离，更新最优解
     */
    private void checkAndCalculateLayout(int[] placement) {
        long currentTotalDistance = 0;

        // 建立一个临时的 设备索引 -> 位置索引 的映射
        Map<Integer, Integer> devToLocMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            devToLocMap.put(i, placement[i]);
        }

        // 遍历所有"设备连接关系"
        for (DeviceConnection conn : deviceConnections) {
            int devIdx1 = deviceIndexMap.get(conn.dev1);
            int devIdx2 = deviceIndexMap.get(conn.dev2);

            // 找到它们被映射到的位置
            int locIdx1 = devToLocMap.get(devIdx1);
            int locIdx2 = devToLocMap.get(devIdx2);

            // 从预处理好的最短路径矩阵中获取距离
            long dist = locationDistMatrix[locIdx1][locIdx2];

            // 步骤 3.a: 判断连接关系有没有被满足
            // 如果距离是无穷大，表示这两个位置不连通，此布局无效
            if (dist == INFINITY) {
                currentTotalDistance = Long.MAX_VALUE; // 标记为无效
                break; // 无需再计算其他连接
            }

            // 步骤 3.b: 满足前提下再计算它的这个距离之和
            currentTotalDistance += dist;
        }

        // 步骤 4: 找到最短的那种距离之和
        if (currentTotalDistance < bestResult.totalDistance) {
            bestResult.totalDistance = currentTotalDistance;
            bestResult.found = true;
            bestResult.layout.clear();
            // 记录这个最优布局
            for (int i = 0; i < m; i++) {
                String devName = devices.get(i);
                String locName = locations.get(placement[i]);
                bestResult.layout.put(devName, locName);
            }
        }
    }

    /**
     * Main 方法：用于测试
     */
    public static void main(String[] args) {
        // 1. 定义设备
        List<String> devices = Arrays.asList("A", "B", "C");
        // 定义设备连接关系
        List<DeviceConnection> deviceConnections = Arrays.asList(
                new DeviceConnection("A", "B"),
                new DeviceConnection("A", "C")
        );

        // 2. 定义位置
        List<String> locations = Arrays.asList("P1", "P2", "P3");
        // 定义位置连接关系
        List<LocationConnection> locationConnections = Arrays.asList(
                new LocationConnection("P1", "P2", 1),
                new LocationConnection("P1", "P3", 1),
                new LocationConnection("P2", "P3", 2)
        );

        // 3. 创建优化器并执行
        DeviceLayoutOptimizer optimizer = new DeviceLayoutOptimizer();
        LayoutResult result = optimizer.findOptimalLayout(
                devices,
                locations,
                deviceConnections,
                locationConnections
        );

        // 4. 打印结果
        System.out.println(result);

        System.out.println("--- 另一个测试：位置不连通 ---");
        // 假设 P2 和 P3 不直接相连，但可以通过 P1
        List<LocationConnection> locationConnections2 = Arrays.asList(
                new LocationConnection("P1", "P2", 1),
                new LocationConnection("P1", "P3", 3)
                // P2 和 P3 不直连，但最短路径是 P2-P1-P3，距离为 1+3=4
        );

        DeviceLayoutOptimizer optimizer2 = new DeviceLayoutOptimizer();
        LayoutResult result2 = optimizer2.findOptimalLayout(
                devices,
                locations,
                deviceConnections,
                locationConnections2
        );
        // 最优解应该是 A:P1, B:P2, C:P3，总距离 = dist(P1,P2) + dist(P1,P3) = 1 + 3 = 4
        // 如果是 A:P2, B:P1, C:P3，总距离 = dist(P2,P1) + dist(P2,P3) = 1 + 4 = 5
        System.out.println(result2);
    }
}