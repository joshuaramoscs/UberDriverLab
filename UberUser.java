//This is the UberUser Obeject class.
public class UberUser implements Comparable{
  //Private Variables
  private String name;
  private String pickUp;
  private String destination;
  private int rideTime;
  private int miles;
  private double earning;
  
  //UberUser(String)
  UberUser(String str){
    String[] values = str.split(",");
    values[3]=values[3].trim();
    //values[3] = values[3].replaceAll("\u00A0", "").trim();;
    values[4]=values[4].trim();
    values[5]=values[5].trim();
    
    this.name=values[0].trim();
    this.pickUp=values[1].trim();
    this.destination=values[2].trim();
    this.rideTime=Integer.parseInt(values[3]);
    this.miles=Integer.parseInt(values[4]);
    this.earning=Double.parseDouble(values[5]);
  }
  
  //Getters
  String getName(){
    return name;
  }
  String getPickup(){
    return pickUp;
  }
  String getDest(){
    return destination;
  }
  int getRideTime(){
    return rideTime;
  }
  int getMiles(){
    return miles;
  }
  double getEarnings(){
    return earning;
  }
  
  //Setters
  public void setName(String name){
    this.name = name;
  }
  public void setPickUp(String pickUp){
    this.pickUp = pickUp;
  }
  public void setDest(String destination){
    this.destination = destination;
  }
  public void setRideTime(int rideTime){
    this.rideTime = rideTime;
  }
  public void setMiles(int miles){
    this.miles = miles;
  }
  public void setEarnings(double earning){
    this.earning = earning;
  }
  
  //toString()
  public String toString(){
    return "Picking up: "+name+" from: "+pickUp+" to: "+destination+". Total Time: "+rideTime+". Total Miles: "
      +miles+". You will earn: $"+earning+".";
  }
  
  //equals()
  public boolean equals(Object o){
    UberUser tmp = (UberUser)o;
    if(this.name.equals(tmp.name)){
      if(this.pickUp.equals(tmp.pickUp)){
        if(this.destination.equals(tmp.destination)){
          if(this.rideTime == tmp.rideTime){
            if(this.miles == tmp.miles){
              if(this.earning == tmp.earning){
                return true;
              }else{
                return false;
              }
            }else{
              return false;
            }
          }else{
            return false;
          }
        }else{
          return false;
        }
      }else{
        return false;
      }
    }else{
      return false;
    }
  }
  
  public int compareTo(Object o){
    UberUser tmp = (UberUser)o;
    if(rideTime == tmp.rideTime){
      if(this.miles == tmp.miles){
        if(this.earning == tmp.earning){
          return 0;
        }else{
          return this.rideTime - tmp.rideTime;
        }
      }else{
        return this.miles - tmp.miles;
      }
    }else{
      return this.rideTime - tmp.rideTime;
    }
  }
}