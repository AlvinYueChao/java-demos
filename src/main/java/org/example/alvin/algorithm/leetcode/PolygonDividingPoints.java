package org.example.alvin.algorithm.leetcode;

public class PolygonDividingPoints {
  public static void main(String[] args) {
    Vertex[] givenVertices = {
      new Vertex(1, 2), new Vertex(1, 10), new Vertex(5, 10), new Vertex(5, 2)
    };
    Polygon fourPolygon = new Polygon(givenVertices);
    Vertex[] threeDividendVertices = fourPolygon.getKdividendVertices(3);

    for (int i = 0; i < threeDividendVertices.length; i++) {
      System.out.println("Coordinate of " + i + " is: " + threeDividendVertices[i].toString());
    }
  }
}

class Vertex {
  private double x;
  private double y;

  public Vertex(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "The coordinate of this vertex is (" + this.x + ", " + this.y + ")";
  }
}

class Polygon {
  private final Vertex[] vertices;

  public Polygon(Vertex[] givenVertices) {
    this.vertices = givenVertices;
  }

  public double getPerimeter() {
    double perimeter = 0;

    for (int i = 0; i < vertices.length; i++) {
      int next = i + 1;

      if (i == vertices.length - 1) {
        next = 0;
      }

      if (vertices[i].getX() == vertices[next].getX()) {
        perimeter += Math.abs(vertices[next].getY() - vertices[i].getY());
      } else {
        perimeter += Math.abs(vertices[next].getX() - vertices[i].getX());
      }
    }

    return perimeter;
  }

  public Vertex[] getKdividendVertices(int k) {
    Vertex[] kVertices = new Vertex[k];
    double kDividendLength = getPerimeter() / k;
    double lengthLeft = kDividendLength;
    int index = 0;

    for (int i = 0; i < vertices.length; i++) {
      int next = i + 1;

      if (i == 0) {
        kVertices[index] = new Vertex(vertices[i].getX(), vertices[i].getY());
        index++;
      }

      if (i == vertices.length - 1) {
        next = 0;
      }

      if (vertices[i].getX() == vertices[next].getX()) {
        double distance = Math.abs(vertices[next].getY() - vertices[i].getY());
        if (lengthLeft <= distance) {
          if (vertices[i].getY() < vertices[next].getY()) {
            double base = 0;
            while (lengthLeft <= distance) {
              kVertices[index] =
                  new Vertex(vertices[i].getX(), vertices[i].getY() + (base + lengthLeft));

              base += lengthLeft;
              distance -= lengthLeft;
              lengthLeft = kDividendLength;
              index++;
            }
          } else {
            double base = 0;
            while (lengthLeft <= distance) {
              kVertices[index] =
                  new Vertex(vertices[i].getX(), vertices[i].getY() - (base + lengthLeft));

              base += lengthLeft;
              distance -= lengthLeft;
              lengthLeft = kDividendLength;
              index++;
            }
          }
        } else {
          lengthLeft -= distance;
        }
      } else {
        double distance = Math.abs(vertices[next].getX() - vertices[i].getX());
        if (lengthLeft <= distance) {
          if (vertices[i].getX() < vertices[next].getX()) {
            double base = 0;
            while (lengthLeft <= distance) {
              kVertices[index] =
                  new Vertex(vertices[i].getX() + (base + lengthLeft), vertices[i].getY());

              base += lengthLeft;
              distance -= lengthLeft;
              lengthLeft = kDividendLength;
              index++;
            }
          } else {
            double base = 0;
            while (lengthLeft <= distance) {
              kVertices[index] =
                  new Vertex(vertices[i].getX() - (base + lengthLeft), vertices[i].getY());

              base += lengthLeft;
              distance -= lengthLeft;
              lengthLeft = kDividendLength;
              index++;
            }
          }
        } else {
          lengthLeft -= distance;
        }
      }
    }

    return kVertices;
  }
}
