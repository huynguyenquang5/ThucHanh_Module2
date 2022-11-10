import java.util.Comparator;

public class AvgScoreComparatorDescending implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Double.compare(o2.getAverageScore(), o1.getAverageScore());
    }
}
