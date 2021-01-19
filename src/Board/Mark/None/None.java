package Board.Mark.None;

import Board.Mark.Mark;

public class None implements Mark{
    String type;

    public None(){
        type = "_";
    }

    public String getType(){
        return type;
    }

    public boolean isNotNone(){
        return false;
    }

    public boolean isSame(Mark mark){
        return mark.getType().equals(getType());
    }

    public boolean isOpposing(Mark mark){
        return mark.isSame(makeOpponentMark());
    }

    public Mark getCopy(){
        return new None();
    }

    public Mark makeOpponentMark(){
        return new None();
    }
}
