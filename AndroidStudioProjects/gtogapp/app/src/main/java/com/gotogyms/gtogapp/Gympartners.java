package com.gotogyms.gtogapp;

/**
 * Created by Jyothsna on 7/4/2017.
 */

public class Gympartners {
    private String gymname;
    private int gym_id;

    public Gympartners(String gymname, int gym_id){
        this.setName(gymname);
        this.setGym_id(gym_id);
    }
    public String getName() {
        return gymname;
    }

    public void setName(String gymname) {
        this.gymname = gymname;
    }

    public int getGym_id() {
        return gym_id;
    }

    public void setGym_id(int gym_id) {
        this.gym_id = gym_id;
    }
}
