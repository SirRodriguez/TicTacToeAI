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

    public Mark getCopy(){
        return new None();
    }
}
