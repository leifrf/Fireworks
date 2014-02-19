
public class SleepPrint {
	

	SleepPrint(Object o){
		System.out.println(o);
		try{Thread.sleep(1000);}
		catch(Exception e){}
	}
}
