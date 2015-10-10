package com.wang.spring.DI;

import java.util.Collection;
import java.util.Map;

public class OneManBand implements Performer{
	public OneManBand() {
	}

	@Override
	public void perform() {
		for(String key : instruments.keySet()) {
			System.out.print(key + " : ");
			Instrument instrument = instruments.get(key);
			instrument.play();
		}
	}

//	private Collection<Instrument> instruments;
	private Map<String,Instrument> instruments;

	public void setInstruments(Map<String,Instrument> instruments) {
		this.instruments = instruments;
	}

}
