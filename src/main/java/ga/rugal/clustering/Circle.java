package ga.rugal.clustering;

/**
 *
 * @author Rugal Bernstein
 */
public class Circle implements Comparable<Circle>
{

    public final int X;

    public final int Y;

    //Start from 1, 0 represent belong to no cluster
    private int belongTo = 0;

    public int getBelongTo()
    {
        return belongTo;
    }

    public boolean inCluster(Circle other)
    {
        if (other.isFree())
        {
            return false;
        }
        else
        {
            return this.belongTo == other.belongTo;
        }
    }

    public boolean isFree()
    {
        return this.belongTo == 0;
    }

    public void setBelongTo(int belongTo)
    {
        this.belongTo = belongTo;
    }

    public Circle(int X, int Y)
    {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public String toString()
    {
        return "Circle{" + "X=" + X + ", Y=" + Y + ", belongTo=" + belongTo + '}';
    }

    public boolean isNearby(Circle other, int radius)
    {
        double pointDistance = Math.pow(this.X - other.X, 2) + Math.pow(this.Y - other.Y, 2);
        double radiusDistance = 4 * Math.pow(radius, 2);
        return pointDistance <= radiusDistance;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Circle other = (Circle) obj;
        if (this.X != other.X)
        {
            return false;
        }
        if (this.Y != other.Y)
        {
            return false;
        }
        if (this.belongTo != other.belongTo)
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Circle other)
    {
        if (this.X < other.X)
        {
            return -1;
        }
        if (this.X > other.X)
        {
            return 1;
        }
        if (this.Y < other.Y)
        {
            return -1;
        }
        if (this.Y > other.Y)
        {
            return 1;
        }
        return 0;
    }

}
