// Countdown for our Game. I still need to find out how to reset every game. I think that adding a loop might be enough

public class countDown {
    static Thread thread = new Thread();
    public static void main (String args[]) throws InterruptedException{
        for (int remainingSeconds = 5; remainingSeconds>=0; remainingSeconds--){
// thread.sleep(1000) will make the remainingSeconds number loop wait 1 between the loops
// The value in the 'thread.sleep' function is in milliseconds
            thread.sleep(1000);
            System.out.println(remainingSeconds);
            if (remainingSeconds == 0){
                System.out.println("Game Over");
            }
        }

    }
}

// I still need to create tests