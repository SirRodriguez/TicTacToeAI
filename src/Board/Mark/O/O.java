package Board.Mark.O;

import Board.Mark.Mark;

public class O implements Mark{
    String type;

    public O(){
        type = "O";
    }

    public String getType(){
        return type;
    }

    public boolean isNotNone(){
        return true;
    }

    public boolean isSame(Mark mark){
        return mark.getType().equals(getType());
    }

    public Mark getCopy(){
        return new O();
    }
}
