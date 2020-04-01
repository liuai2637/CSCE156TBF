package unl.cse.trucks;

public class ListTester {

	public static void main(String[] args) {

		// TODO: Test cases should be made here
		TruckList tl = new TruckList();
		Truck t = new Truck.Builder().licensePlate("ABC 123").build();
		
/**
		//adding 10 random trucks
		for (int i = 0; i < 10; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToStart(t1);
		}
		tl.print();
		tl.clear();		

		// add 3 succesive trucks to the start
		tl.addToStart(t);
		for (int i = 0; i < 3; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToStart(t1);
		}
		tl.print();
		tl.clear();

		// add 3 succesive trucks to the end
		tl.addToStart(t);
		for (int i = 0; i < 3; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToEnd(t1);
		}
		tl.print();
		tl.clear();

		// add 6 trucks alternating
		tl.addToStart(t);
		for (int i = 0; i < 3; i++) {
			Truck t1 = Truck.createRandomTruck();
			Truck t2 = Truck.createRandomTruck();
			tl.addToStart(t1);
			tl.addToEnd(t2);
		}

		tl.print();
		tl.clear();

		//add 3 trucks remove 1st one
		for (int i = 0; i < 3; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToStart(t1);
		}
		tl.print();
		tl.remove(0);
		tl.print();
		tl.clear();
	
		//add 3 trucks remove last one
		for (int i = 0; i < 3; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToStart(t1);
		}
		tl.print();
		tl.remove(tl.size - 1);
		tl.print();
		tl.clear();
**/	
		//add 5 remove middle the last the first the last
		for (int i = 0; i < 5; i++) {
			Truck t1 = Truck.createRandomTruck();
			tl.addToStart(t1);
		}
		tl.print();
		tl.remove((tl.size - 1) / 2);
		tl.print();
		tl.remove(tl.size - 1);
		tl.print();
		tl.remove(0);
		tl.print();
		tl.remove(tl.size - 1);
		tl.print();
		tl.remove(0);
		tl.print();
		tl.clear();
	}
}
