package Board.Mark;

public interface Mark {
    public String getType();
    public boolean isNotNone();
    public boolean isSame(Mark mark);
    public boolean isOpposing(Mark mark);
    public Mark getCopy();
    public Mark makeOpponentMark();
}
