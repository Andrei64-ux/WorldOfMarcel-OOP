package cell;

public class Cell{
    public int ox;
    public int oy;
    public CellType type;
    public boolean isVisited;
    public CellElement element;

    public Cell(int ox, int oy,
                CellType type, boolean isVisited,
                CellElement element) {
        this.ox = ox;
        this.oy = oy;
        this.type = type;
        this.isVisited = isVisited;
        this.element = element;
    }

    @Override
    public String toString() {
        if(!isVisited){
            return "?";
        }
        else if(type == CellType.FINISH){
            return "F";
        }
        else if(type == CellType.EMPTY){
            return ".";
        }
        else {
            return element.toCharacter();
        }
    }
}
