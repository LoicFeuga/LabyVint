/**
 * 
 * Universit� de Nice - D�partement Informatique
 * Ann�e 2013-2014 - S3T
 * 
 * @version V0_0_3
 * 
 * -Edition A : 
 *		+Version V0_0_0 : version de d�part 
 *						+methode chargementFichierTexte
 *						+methode sauvegardeFichierTexte 
 * 						+methode chargementFichierImage
 * 		+Version V0_0_1 : 
 * 						+methode store
 * 						+methode load
 * 		+Version V0_0_2 :
 * 						+methode recup_config
 * 						+methdoe obtenirString
 * 		+Version V0_0_3 :
 * 						+methode obtenirFichiers
 *      +Version V0_0_5 :
 *                      +methode recupTextInFile
 *                      +Mise � jour JavaDoc
 *                      +methode getNbLine
 * @author Loic
 * 
 */
package controleur;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;


/**
 * La  Class VFichier, a pour objectif de proposer tous les services
 *   possibles sur des fichiers
 */
public class VFichier extends File{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private String name;
    /**
     * Instantiates a new VFichier.
     *
     * @param arg0 the arg0
     * @param arg1 the arg1
     */
    public VFichier(File arg0, String arg1) {super(arg0, arg1);}

    /**
     * Instantiates a new VFichier.
     *
     * @param arg0 the arg0
     */
    public VFichier(String arg0) { super(arg0); name = arg0;}

    /**
     * Instantiates a new VFichier.
     *
     * @param fichier the fichier
     */
    public VFichier(File fichier){super("");}




    /**
     * La m�thode chargementFichierTexte renvoie un String de tous le fichier
     *   dont le nom de fichier est pass� en op�rande
     *
     * @param nomFichier le nom fichier � aller lire
     * @return le String du fichier
     */
    public static String chargementFichierTexte (String nomFichier){

        if(nomFichier == null || nomFichier.length() == 0)
            return null;

        File fichier = new File(nomFichier);

        if(fichier.isDirectory()) return null;

        String contenuFichier = new String();
        BufferedReader lecture = null;

        try {

            lecture = new BufferedReader(new FileReader(nomFichier));
            String s = new String();

            while((s=lecture.readLine())!=null)
            {
                if(s!=null);
                contenuFichier += s+"\r\n";//on recup�re le contenu
            }
        } catch (IOException e) {
            System.out.println("Erreur sur la lecture du fichier: "+ nomFichier);
            System.out.println(e);
            return null;
        }
        finally{
            try {lecture.close();} 
            catch (IOException e) {	e.printStackTrace();}
        }
        return contenuFichier;


    }

    /**
     * Sauvegarde un fichier texte chez l'utilisateur au chemin pass� en op�rande 1
     *   dont le contenu pass� en op�rande 2 est un String
     *
     * @param nomFichier le nom fichier � enregistrer
     * @param contenu le contenu du fichier
     * @return true, si ok
     */
    public static boolean sauvegardeFichierTexte (String nomFichier, String contenu ){

        if(nomFichier == null || nomFichier.length() == 0)
            return false;

        File fichier = new File(nomFichier);

        if(fichier.isDirectory()) return false;

        String cont = contenu.replace("\n", "\r\n");

        if(!fichier.exists()){
            try {
                fichier.createNewFile();
            } catch (IOException e) {
                System.out.printf("Impossible de creer le fichier: " + fichier.getName() +" car : " +e.getMessage());
                return false;
            }
        }

        PrintWriter ecriture = null;

        try {
            //on ouvre un flux d'�criture
            ecriture = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
            ecriture.println(cont);

        } catch (IOException e) {
            System.out.println("Erreur lors de l'ecriture dans le fichier: "+ nomFichier);
            return false;
        }
        finally{
            ecriture.close();// on ferme le flux
        }

        return true;
    }

    /**
     * Charge une image stock� dont le chemin est pass� en op�rande
     * 
     * Un BufferedImage sera alors return, correspond � l'image
     *  situ�e au chemin pr�cis�
     *
     * @param chemin Le chemin de l'image
     * @return L'image logique
     */
    public static BufferedImage chargementFichierImage(String chemin){

        File fichier = new File(chemin);

        if(!fichier.isFile()) return null;

        BufferedImage image = null;
        try {image = ImageIO.read(fichier);}
        catch (IOException e) {e.printStackTrace();}

        return image;
    }


    /**
     * Store, n'importe quel object config � un name.
     *
     * @param config the config
     * @param name the name
     * @return true, si ok
     */
    @SuppressWarnings("resource")
    public static boolean store (Object config, String  name ) {   
        FileOutputStream f= null;
        ObjectOutputStream out= null;

        if (config == null) return false;

        try {f= new FileOutputStream(name);}
        catch (Exception e) {return false;}

        try {out= new ObjectOutputStream(f);}
        catch (Exception e) {return false;}

        try{out.writeObject(config);}
        catch (Exception e) {return false;}

        System.out.println("Enregistrement du fichier " + name + " : OK");
        return true;
    }

    /**
     * Load n'importe quel fichier stock� au chemin pass� en op�rande 
     * 
     * Le return sera alors un Object, est n�cessitera certainement 
     * un cast 
     * 
     * @param name Le nom du fichier
     * @return L'objet 
     */
    @SuppressWarnings("resource")
    public static Object load (String  name) {
        FileInputStream f= null;
        ObjectInputStream in= null;
        Object resultat;

        try {f= new FileInputStream(name);}
        catch (Exception e) {return null;}

        try {in= new ObjectInputStream(f);}
        catch (Exception e) {return null;}

        try{resultat=in.readObject();}
        catch (Exception e) {return null;}

        System.out.println("Chargement du fichier " + name + " : OK");
        return resultat;
    }


    /**
     * Obtenir string, elle renvoie le String d'un Fichier pass� en op�rande
     *
     * @param fichier le fichier � lire
     * @return la traduction du fichier en string
     */
    public String obtenirString(VFichier fichier){
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fichier));
            StringWriter out = new StringWriter();
            int b;
            while ((b=in.read()) != -1)
                out.write(b);
            out.flush();
            out.close();
            in.close();
            return out.toString();
        }
        catch (IOException ie)
        {
            ie.printStackTrace(); 
        }
        return "";
    }


    //------ M�thode obtenirFichiers
    /**
     * Obtenir fichiers, elle r�cup�re tous les fichiers d'un dossier pass� en op�rande, 
     * elle les mettra dans une ArrayList pour le retourner remplis : 
     * 1 => fichier1
     * 2 => fichier2
     * ..
     * ..
     * 
     * @param dossier le dossier � analyser
     * @return le hashmap de tous les fichiers 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList obtenirFichiers(VFichier dossier){
        ArrayList retur = new ArrayList();

        for(int i = 0 ; i< dossier.list().length ; i ++)
            retur.add(dossier.getPath()+"/"+dossier.list()[i]);


        return retur;
    }


    // --------------------- M�thode recup_config
    /**
     * Recup_config, r�cup�re dans un String la valeur d'une config pass� en pam�tre 
     * jusqu'� un saut de ligne
     * String total : "Source : azer"
     * String config : "Source : "
     * le retour sera : "azer"
     *
     * @param config the config
     * @param total the total
     * @return the string
     */
    public static String recup_config(String config,String total){
        int bloc = total.indexOf(config);
        int bloc2 = bloc + config.length();
        String loption= "";

        for(int i = bloc2; total.charAt(i+1) != '\n'; i++){
            loption+=total.charAt(i);
            if(total.charAt(i) == ','){

            }
        }
        return loption;
    }

    
    /**
     * recupTextInFile r�cup�re tous le texte d'un fichier dont le chemin
     *   est pass� en op�rande.
     * Le fichier sera enti�rement lu est toutes les lignes seront plac�es
     *   dans un ArrayList
     * 
     * Il faudra faire attention donc, au espace
     * Les sauts de ligne ("\n") sont donc la condition pour remplir un nouvel
     * �l�ment de l'ArrayList
     * 
     * 
     * @param lien vers le fichier 
     * @return un ArrayList contenant le texte 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    //Test� ok, 
    //1 erreur pour les lignes vides
    public static ArrayList recupTextInFile(String lien){
        ArrayList al = new ArrayList<>();

        String l = VFichier.chargementFichierTexte(lien);

        for (String s : l.split("\r") ){
            //afin d'enlever le dernier �l�ment du tableau � am�liorer bien sur
            //Delete and see...
            if(s.length() > 1){
                s = s.replace("\n","");
                al.add(s);
            }
        }      
         
        return al;
    }
    
    /**
     * M�thode getNbLine, r�cup�re le nombre de ligne d'un VFichier
     * @return le nombre de ligne en int
     */
    //Test� ok
    public int getNbLine(){
        int count = 0;
        
        FileInputStream fis = null;
        try { fis = new FileInputStream(name); } 
        catch (FileNotFoundException e) { e.printStackTrace();}
        @SuppressWarnings("resource")
        LineNumberReader l = new LineNumberReader(       
               new BufferedReader(new InputStreamReader(fis)));
                      try {
                        while ((l.readLine())!=null)
                         {
                            count = l.getLineNumber();
                         }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                      
                      return count;
    }
}
