package com.ubs.ubs;

public class Ubsimages {
    private String imname;
    private int im_id;

    public Ubsimages(String gymname, int gym_id){
        this.setName(imname);
        this.setIm_id(im_id);
    }
    public String getName() {
        return imname;
    }

    public void setName(String imname) {
        this.imname = imname;
    }

    public int getIm_id() {
        return im_id;
    }

    public void setIm_id(int gym_id) {
        this.im_id = im_id;
    }
}
