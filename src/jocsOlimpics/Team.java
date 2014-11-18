package jocsOlimpics;

import java.util.ArrayList;

public class Team {
	
	String name;
	ArrayList<Athlete> athletes;
	Relay relay;
	

	public Team(String name)
	{
		this.name 		= name;
		this.athletes 	= new ArrayList<Athlete>();
		this.relay = new Relay();
	}
	
	public Team(String name, ArrayList<Athlete> athletes)
	{
		this.name 		= name;
		this.athletes 	= athletes;
		this.relay = new Relay();
		setRelayToAthletes();
		
	}
	
	public Relay getRelay()
	{
		return relay;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Athlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(ArrayList<Athlete> athletes) {
		this.athletes = athletes;
	}
	public void setAthlete(Athlete athlete)
	{
		athlete.setRelay(this.relay);
		this.athletes.add(athlete);
		
	}
	
	private void setRelayToAthletes()
	{
		for (Athlete at : athletes)
		{
			at.setRelay(relay);
		}
	
	}
	
}
