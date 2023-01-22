package sn.toto.crudetudiantjavafx;

public class Etudiant {
    private int id;
    private String prenom, nom, matiere;

    public Etudiant() {
    }

    public Etudiant(int id, String prenom, String nom, String matiere) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.matiere = matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
