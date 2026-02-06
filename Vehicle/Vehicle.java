import java.time.Year;

public class Vehicle {
    public String brand;
    public String model;
    public Year yearofMfg;
    public String color;
    public char fuelType; // E= electric , C=CNG, D=diesel , P = petrol. N= null
    public double price;
    public int seats;
    private String mfgCode;
    private int noOfServices;
   

    public void setMfgCode(String code){
        mfgCode = code;
    }
    public String getMfgCode(){
        return mfgCode;
    }

    public void setNoOfServices(int nos){
        noOfServices = nos;
    }
    public int getNoOfServices(){
        return noOfServices;
    }

    public Vehicle(){
        brand = "Tata";
        model = "Nexon";
        yearofMfg = Year.of(2021);
        color = "Green";
        fuelType = 'P';
        seats = 5;
        price = 1250000.99;
        
    }

    public Vehicle(String brand, String model, Year yearofMfg, String color, double price){
        this.brand = brand;
        this.model = model;
        this.yearofMfg = yearofMfg;
        this.color = color;
        this.price = price;
    }

    public Vehicle(int seats, double price, char fuelType, String mfgCode, int noOfServices){
        this.seats = seats;
        this.price = price;
        this.fuelType = fuelType;
        this.mfgCode = mfgCode;
        this.noOfServices = noOfServices;
    }


    public void start(){
        System.out.println("Start ignition by pressing the button.");
        System.out.println("Your initital speed is 20 kmph");
    }
    public void stop(){
        System.out.println("Press the button to stop the engine.");
        System.out.println("Your vehicle is stopped");

    }
    public void drive(){
        System.out.println("Accelerate to drive.");
        System.out.println("Speed increased by 20.");
    }
    public int accelerate(int initSp){
        return initSp += 20;
    }
    public float calcMileage(float fuelAmt, float dist){
        return dist/fuelAmt;
    }

    
    public static void printDetails(Vehicle v){
        System.out.println("-----------------------------------------");
        System.out.println("------------Vehicle Details--------------");
        System.out.println("-----------------------------------------");
        System.out.println("Brand Name: " + v.brand);
        System.out.println("Model: " + v.model);
        System.out.println("Year of MFG: " + v.yearofMfg);
        System.out.println("Color of the vehicle: " + v.color);
        System.out.println("Fuel Type: " + v.fuelType);
        System.out.println("Number of seats: " + v.seats);
        System.out.println("Price of the vehicle: " + v.price);
        System.out.println("Mfg Code: " + v.getMfgCode());
        System.out.println("Number of Services done: " + v.getNoOfServices());
        System.out.println("-----------------------------------------");
    }
}
