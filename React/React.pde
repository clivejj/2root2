Ellie[] elevators;
ControlTower please = new ControlTower(20, 10, 50);

void setup(){
  size(1200, 400);
  elevators = new Ellie[please.ellies.size()];
  int xcor = (int)(width/(2*elevators.length+1));
  for (int i = 0; i < elevators.length; i++) {
    elevators[i] = new Ellie(xcor, height/3, width/(2*elevators.length+1), height/4);
    xcor+=width/(elevators.length+1);
  }
 void draw(){
   int[] ranges = please.loopy(3600);
   for (int i = 0; i < elevators.length; i++){
     setColor(i);
   }
}