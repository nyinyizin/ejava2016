/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class Pod implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pod_id")
    private int podId;

    @OneToOne
    @JoinColumn(name = "pkg_id", referencedColumnName = "pkg_id")
    @NotNull
    private int pkgId;

    @Column(name = "note")
    private String note;

    @Column(name = "image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob image;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deliveryDate;

    @Column(name = "ack_id")
    private String ackId;

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public int getPkgId() {
        return pkgId;
    }

    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }
}
