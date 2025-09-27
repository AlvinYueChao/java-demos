题目2: 公司数据库中有“项目表”和“部门表”： 部门表
department (编号id, 部门名称name) 项目表 project (编号id, 项目名name, 归属部门dept_id) 写一句SQL: 统计各部门的项目数量 输出示例:
部门名字 项目数量 销售部 3

select d.name, count(p.id) as count
from department d
left join project p on d.id = p.dept_id
group by d.name;