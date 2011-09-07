package org.kata;

public class Bowling 
{

	protected enum Bonus { Strike, Spare, PinNumbers, Cero }  // X :- Strike, 7/ :- Spare,  56 o 6- :- PinNumbers, no valid :- Cero
	
	protected Bonus checkFrame(String frames, int position) {
		if (frames.charAt(position) == 'X')
			return Bonus.Strike;
		else if (frames.charAt(position) == '0')
			return Bonus.Cero;
		else if (frames.charAt(position + 1) == '/')
			return Bonus.Spare;
		else
			return Bonus.PinNumbers;
	}
	
	protected int valuePin(String pin) {
		try {
			return Integer.valueOf(pin);
		} catch (NumberFormatException ex) {
			return 0;
		}
		
	}
	
	private String getPin(String frames, int positionStart, int positionEnd) {
		return (positionEnd < frames.length()) ? frames.substring(positionStart, positionEnd) : frames.substring(positionStart);
	}
	
	protected int valueFrame(String frames, int position, boolean fullFrame) {
		if (frames.charAt(position) == 'X')
			return 10;
		else if (frames.charAt(position) == '0')
			return 0;
		else if (position + 1 < frames.length() && frames.charAt(position + 1) == '/')
			 // if not fullFrame: solamente analizamos el primer pin/lanzamiento
			return (!fullFrame) ? valuePin(getPin(frames, position, position + 1)) : 10;
		else
			// if not fullFrame: solamente analizamos el primer pin/lanzamiento else: analizamos el primero y segundo pin/lanzamiento
			return (!fullFrame) ? valuePin(getPin(frames, position, position + 1)) 
								: valuePin(getPin(frames, position, position + 1)) + valuePin(getPin(frames, position + 1, position + 2));
	}

	protected int bonusFrame(String frames, int position, Bonus bonus) {
		switch (bonus) {
		    case Strike: 
		    	int bonusTotal = 10 + valueFrame(frames, position, true);
		    	if (position + 1 < frames.length()  && checkFrame(frames, position) == Bonus.Strike)
		    		bonusTotal += valueFrame(frames, position + 1, false);
	    		return bonusTotal;
		    case Spare: 
		    	return 10 + valueFrame(frames, position, false);
		    default: 
		    	return 0;
		}
	}
	
	public int score(String frames) {
		int score = 0;
		int frame = 1;
		for (int pos = 0; pos < frames.length() && frame <= 10; pos++, frame++) {
			if (checkFrame(frames, pos) == Bonus.Strike)
				score += (pos + 1 < frames.length()) ? bonusFrame(frames, pos + 1, Bonus.Strike) : (frame == 10) ? 12 : 0;
			else if (checkFrame(frames, pos) == Bonus.Spare) {
				score += (pos + 2 < frames.length()) ? bonusFrame(frames, pos + 2, Bonus.Spare) : (frame == 10) ? 11 : 0;
				pos += 1;
			} else if (checkFrame(frames, pos) == Bonus.PinNumbers) {
				score += valueFrame(frames, pos, true);
				pos += 1;
			}
		}
		
		return score;
	}
}
