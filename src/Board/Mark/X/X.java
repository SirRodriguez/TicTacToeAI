package Board.Mark.X;

import Board.Mark.Mark;
import Board.Mark.O.O;

public class X implements Mark{
    String type;

    public X(){
        type = "X";
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
        return new X();
    }

    public Mark makeOpponentMark(){
        return new O();
    }
}
