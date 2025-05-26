import java.util.Timer;

public class Plants {
    private int spawn_timer;

    public void new_plant(){

    }

    public void delete_plant(){

    }

    public void spawn_new_plant(){
        if (spawn_timer >= 10){
            new_plant();

            spawn_timer = 0;
        }

        spawn_timer++;
    }
}
