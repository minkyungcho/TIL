package day05;

public class c3Q {
	    public String solution(String[] participant, String[] completion) {
	       String answer = "";
	        int i=0;
	       while(i<participant.length){
	           go : for(int j=0; j<completion.length; j++){
	               if(participant[i].equals(completion[j])){
	                   participant[i] = "";
	                   completion[j] = "";
	                   break go;
	               }
	           }
	           i++;
	       }
	        for(int k=0; k<participant.length; k++){
	            if(participant[k] != ""){
	                answer = participant[k];
	            }
	        }
	        return answer;
	    }
	}

