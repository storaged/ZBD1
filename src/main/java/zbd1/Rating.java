package zbd1;

public enum Rating {
	UNKNOWN(0),
	HORRIBLE(1), 
	WEAK(2), 
	AVERAGE(3),
	GOOD(4), 
	DEVINE(5);

    private int rate;

    Rating(int rate) {
    	this.rate = rate;
    }
    
    public int toInt() {
    	return rate;
    }
    public static Rating fromInt(int rate) {    
    	switch(rate) {
           case 1: return HORRIBLE;
           case 2: return WEAK;
           case 3: return AVERAGE;
           case 4: return GOOD;
           case 5: return DEVINE;
           default:
                   return UNKNOWN;
       }
    }
     
    public String toString() {
    	switch(this) {
        	case HORRIBLE:
        		return "Horrible";
        	case WEAK:
        		return "Weak";
        	case AVERAGE:
        		return "Average";
        	case GOOD:
        		return "Good";
        	case DEVINE:
        		return "Devine";
        	default:
        		return "Unknown";
        }
    }	
}
