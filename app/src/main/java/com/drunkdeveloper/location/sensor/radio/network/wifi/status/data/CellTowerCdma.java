package com.drunkdeveloper.location.sensor.radio.network.wifi.status.data;

import java.util.Locale;

public class CellTowerCdma extends CellTower {
    public static final String FAMILY = "cdma";

    private int bsid;
    private int nid;
    private int sid;

    public CellTowerCdma(int sid, int nid, int bsid) {
        super();
        this.setSid(sid);
        this.setNid(nid);
        this.setBsid(bsid);
    }

    public int getBsid() {
        return bsid;
    }

    public int getNid() {
        return nid;
    }

    public int getSid() {
        return sid;
    }

    /**
     * Returns the cell identity in text form.
     * <p>
     * For CDMA-like networks this string has the following form:
     * <p>
     * {@code cdma:sid-nid-bsid}
     * <p>
     * The first is a string which uniquely identifies the network family.
     * It is followed by a colon and a sequence of System ID, Network ID and
     * Base Station ID, separated by dashes and with no leading zeroes.
     */
    @Override
    public String getText() {
        return getText(sid, nid, bsid);
    }

    /**
     * Converts a SID/NID/BSID tuple to an identity string, or {@code null}
     * if BSID is invalid.
     */
    public static String getText(int sid, int nid, int bsid) {
        int iSid = ((sid == -1) || (sid == Integer.MAX_VALUE)) ? CellTower.UNKNOWN : sid;
        int iNid = ((nid == -1) || (nid == Integer.MAX_VALUE)) ? CellTower.UNKNOWN : nid;
        if ((bsid == -1) || (bsid == Integer.MAX_VALUE))
            return null;
        else
            return String.format(Locale.getDefault(), "%s:%d-%d-%d", FAMILY, iSid, iNid, bsid);
    }

    public void setBsid(int bsid) {
        if ((bsid != Integer.MAX_VALUE) && (bsid != -1))
            this.bsid = bsid;
        else
            this.bsid = CellTower.UNKNOWN;
    }

    public void setNid(int nid) {
        if ((nid != Integer.MAX_VALUE) && (nid != -1))
            this.nid = nid;
        else
            this.nid = CellTower.UNKNOWN;
    }

    public void setSid(int sid) {
        if ((sid != Integer.MAX_VALUE) && (sid != -1))
            this.sid = sid;
        else
            this.sid = CellTower.UNKNOWN;
    }
}
