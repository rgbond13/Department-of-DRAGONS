public class MyProgram extends ConsoleProgram
{
    public void run()
    {
        String archerName = readLine("Enter name of Archer: ");
        Archer archer = new Archer(archerName);

        String mageName = readLine("Enter name of Mage: ");
        Mage mage = new Mage(mageName);
        
        boolean isArchersTurn = true;
        while (archer.isAlive() && mage.isAlive()) {
            System.out.println(archer.getUsername() + "'s health: " + archer.getHealth());
            System.out.println(mage.getUsername() + "'s health: " + mage.getHealth());
            
            String username;
            if (isArchersTurn) {
                username = archer.getUsername();
            } else {
                username = mage.getUsername();
            }
            System.out.println(username + "'s turn!");
            
            int choice = 0;
            while (choice != 1 && choice != 2)
            {
                System.out.println("1. Bandage");
                if (isArchersTurn) {
                    System.out.println("2. Shoot Arrow");
                } else {
                    System.out.println("2. Fireball");
                }
                choice = readInt("Make a selection: ");
            }
            
            if (choice == 1) {
                if (isArchersTurn) {
                    archer.bandage();
                } else {
                    mage.bandage();
                }
            } else if (choice == 2) {
                if (isArchersTurn) {
                    archer.shootArrow(mage);
                } else {
                    mage.castFireball(archer);
                }
            }
            
            isArchersTurn = !isArchersTurn;
        }
    }
}
