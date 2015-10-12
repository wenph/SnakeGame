import com.alibaba.cell.Cell;
import com.alibaba.worm.Worm;
import com.alibaba.worm.WormStage;
import org.junit.Test;

/**
 * Created by pinghua.wph on 2015/10/12.
 */
public class TestCase {

    @Test
    public void testWormInit(){
        System.out.println("create worm");
        Worm worm = new Worm();
        System.out.println(worm);
    }

    @Test
    public void testWormContains(){
        System.out.println("test worm contains method");
        Worm worm = new Worm();
        System.out.println(worm.contains(2,0));
        System.out.println(worm.contains(5,6));
    }

    @Test
    public void testWormStage(){
        System.out.println("create worm stage");
        WormStage stage = new WormStage();
        System.out.println(stage);
    }

    @Test
    public void testCreep(){
        System.out.println("test creep");
        Worm worm = new Worm();
        System.out.println(worm);
        worm.creep();
        System.out.println(worm);
    }

    @Test
    public void testCreepForFood(){
        System.out.println("test creep for food");
        Worm worm = new Worm();
        Cell food = new Cell(1,2);
        System.out.println(worm);
        System.out.println(worm.creep(Worm.DOWN, food));
        System.out.println(worm);
        System.out.println(worm.creep(Worm.DOWN, food));
        System.out.println(worm);
        System.out.println(worm.creep(Worm.RIGHT, food));
        System.out.println(worm);
    }

    @Test
    public void testHit(){
        System.out.println("test hit");
        Worm worm = new Worm();
        Cell food = new Cell(10, 10);
        System.out.println(worm);
        System.out.println(worm.creep(Worm.DOWN, food));
        System.out.println(worm);
        System.out.println(worm.creep(Worm.DOWN, food));
        System.out.println(worm);
        System.out.println(worm.hit(Worm.LEFT));//true
        System.out.println(worm.hit(Worm.RIGHT));//false
        System.out.println(worm.creep(Worm.RIGHT, food));
        System.out.println(worm);
        System.out.println(worm.creep(Worm.RIGHT, food));
        System.out.println(worm);
        System.out.println(worm.creep(Worm.UP, food));
        System.out.println(worm);
        System.out.println(worm.hit(Worm.UP));//true
    }
}
