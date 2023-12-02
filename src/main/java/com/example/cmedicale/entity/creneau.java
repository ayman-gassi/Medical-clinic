package com.example.cmedicale.entity;

public class creneau {
        private int id;
        private int version;
        private int Hdebut;
        private int Hfin;
        private int Mdebut;
        private int Mfin;
        private int id_medecin;
        public creneau(int version, int hdebut, int hfin, int mdebut, int mfin, int id_medecin) {
            this.version = version;
            Hdebut = hdebut;
            Hfin = hfin;
            Mdebut = mdebut;
            Mfin = mfin;
            this.id_medecin = id_medecin;
        }
        public creneau(int id, int version, int hdebut, int hfin, int mdebut, int mfin, int id_medecin) {
            this.id = id;
            this.version = version;
            Hdebut = hdebut;
            Hfin = hfin;
            Mdebut = mdebut;
            Mfin = mfin;
            this.id_medecin = id_medecin;
        }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getHdebut() {
            return Hdebut;
        }

        public void setHdebut(int hdebut) {
            Hdebut = hdebut;
        }

        public int getHfin() {
            return Hfin;
        }

        public void setHfin(int hfin) {
            Hfin = hfin;
        }

        public int getMdebut() {
            return Mdebut;
        }

        public void setMdebut(int mdebut) {
            Mdebut = mdebut;
        }

        public int getMfin() {
            return Mfin;
        }

        public void setMfin(int mfin) {
            Mfin = mfin;
        }

        public int getId_medecin() {
            return id_medecin;
        }

        public void setId_medecin(int id_medecin) {
            this.id_medecin = id_medecin;
        }
}
