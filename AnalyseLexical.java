
package AnalyseurLexical;


import java.util.Scanner;

public class AnalyseLexical {

    // Méthode pour vérifier si c'est une assignation
    private static int assign(char car) {
        if (car == ':'){ return 0;
        }else if (car == '='){ return 1;
        }else {return 2;}
    }
    private static String checkAssignation(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {1, 2, -1},
            {-1, 2, -1},
            {-1, -1, -1}
        };
        int Ec = 0;
        int Ef = 2;
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = assign(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        if (id.charAt(i) == '#' && Ec == Ef && i == id.length() - 1) {
            return "Assignation";
        }
        return null;
    }

    

    // Méthode pour vérifier si c'est une chaîne de caractère
    private static int chaine(char car) {
        if (car == '"') {return 0;
        }else if (car == '\''){ return 1;
        }else{ return 2;}
    }
    private static String checkChaine(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {1, 3, -1},
            {2, -1, 1},
            {-1, -1, -1},
            {-1, 4, 3}, 
            {-1, -1, -1}
        };
        int Ec = 0;
        int[] Ef = {2, 4};
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = chaine(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        boolean estFinal = false;
        for (int f : Ef) {
            if (Ec == f) {
                estFinal = true;
                break;
            }
        }
        if (id.charAt(i) == '#' && estFinal && i == id.length() - 1) {
            return "Chaine";
        }
        return null;
    }

    

    // Méthode pour vérifier si c'est un commentaire 
       private static int comment(char car) {
        if (car == '#') {return 0;
        }else if (car == '\n') {return 1;
        }else return 2;
    }

    private static String checkCommentaire(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {1, -1, -1},
            {1, 2, 1},  
            {-1, -1, -1}
        };
        int Ec = 0;
        int[] Ef = {1, 2};
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = comment(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        boolean estFinal = false;
        for (int f : Ef) {
            if (Ec == f) {
                estFinal = true;
                break;
            }
        }
        if (id.charAt(i) == '#' && estFinal && i == id.length() - 1) {
            return "Commentaire";
        }
        return null;
    }

 
    // Méthode pour vérifier si c'est un espace
       private static int space(char car) {
        if (car == ' '){ return 0;
        }else if (car == '\\') {return 1;
        }else if (car == 't') {return 2;
        }else if (car == 'n'){ return 3;
        }else{ return 4;}
    }

    private static String checkEspace(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {2, 1, -1, -1, -1},
            {-1, -1, 2, 2, -1},
            {-1, -1, -1, -1, -1}
        };
        int Ec = 0;
        int Ef = 2;
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = space(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        if (id.charAt(i) == '#' && Ec == Ef && i == id.length() - 1) {
            return "Espace";
        }
        return null;
    }

 
    // Méthode pour vérifier si c'est un mot-clé ou un ID
       private static int idChar(char car) {
        if ((car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z')) {return 0;
        }else if (car >= '0' && car <= '9'){ return 1;
        }else if (car == '_') {return 2;
        }else{return 3;}
    }

    private static String checkKeyWordOrId(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {1, -1, -1, -1},
            {1, 1, 2, -1},
            {1, 1, 2, -1}
        };
        int Ec = 0;
        int Ef = 1;
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = idChar(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        if (id.charAt(i) == '#' && Ec == Ef && i == id.length() - 1) {
            String[] KeyWord = {"if", "else", "lambda", "True", "False", "print", "Chaima", "Oukachbi"};  // Fix: "lamda" -> "lambda"
            boolean found = false;
            for (String kw : KeyWord) {
                if (idA.equalsIgnoreCase(kw)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                return "Key Word";
            } else {
                return "ID";
            }
        }
        return null;
    }

 
    // Méthode pour vérifier si c'est un nombre 
       private static int number(char car) {
        if (car >= '0' && car <= '9'){ return 0;
        }else if (car == '.') {return 1;
        }else{ return 2;} 
    }

    private static String checkNombre(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {0, 1, -1},
            {1, -1, -1}
        };
        int Ec = 0;
        int[] Ef = {0, 1};
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = number(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        boolean estFinal = false;
        for (int f : Ef) {
            if (Ec == f) {
                estFinal = true;
                break;
            }
        }
        if (id.charAt(i) == '#' && estFinal && i == id.length() - 1) {
            boolean isFloat = false;
            for (char ch : idA.toCharArray()) {
                if (ch == '.') {
                    isFloat = true;
                    break;
                }
            }
            if (isFloat) {
                return "Number (Float)";
            } else {
                return "Number (Int)";
            }
        }
        return null;
    }

 
    // Méthode pour vérifier si c'est un opérateur
       private static int op(char car) {
        if (car == '=') {return 0;
        }else if (car == '<') {return 1;
        }else if (car == '>') {return 2;
        }else if (car == '!') { return 3;
        }else if (car == '+') {return 4;
        }else if (car == '-') {return 5;
        }else if (car == '%') {return 6;
        }else if (car == '/') {return 7;
        }else if (car == '*') {return 8;
        } else {return 9;}
    }

    private static String checkOperateur(String idA) {
        String id = idA + "#";
        int i = 0;
        int[][] MAT = {
            {1, 1, 1, 1, 2, 2, 2, 2, 2, -1},
            {2, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        int Ec = 0;
        int[] Ef = {1, 2};
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = op(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        boolean estFinal = false;
        for (int f : Ef) {
            if (Ec == f) {
                estFinal = true;
                break;
            }
        }
        if (id.charAt(i) == '#' && estFinal && i == id.length() - 1) {
            return "Operateur";
        }
        return null;
    }
     // Méthode pour vérifier si c'est un séparateur
    private static int sep(char car) {
        if (car == '.'){ return 0;
        }else if(",;\"'(){}[]:".indexOf(car) != -1) {return 1;
        }else{ return 2;}
    }

    private static String checkSeparateur(String token) {
        String id = token + "#";
        int i = 0;
        int[][] MAT = {
            {2, 1, -1},  
            {-1, -1, -1},  
            {3, -1, -1}, 
            {4, -1, -1},  
            {-1, -1, -1}  
        };
        int Ec = 0;
        int[] Ef = {1, 2, 4};
        char Tc = id.charAt(i);
        while (Tc != '#') {
            int c = sep(Tc);
            if (c != -1 && MAT[Ec][c] != -1) {
                Ec = MAT[Ec][c];
                i++;
                Tc = id.charAt(i);
            } else {
                break;
            }
        }
        boolean estFinal = false;
        for (int f : Ef) {
            if (Ec == f) {
                estFinal = true;
                break;
            }
        }
        if (id.charAt(i) == '#' && estFinal && i == id.length() - 1) {
            return "Separateur";
        }
        return null;
    }
 
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Entrer un mot:");
    String ligne = input.nextLine();
    
        int i = 0;
        while (i < ligne.length()) {
        // Ignorer les espaces blancs au début d'un token
        while (i < ligne.length() && Character.isWhitespace(ligne.charAt(i))) {
            i++; // Avancer jusqu'au prochain non-espace
        }
        if (i >= ligne.length()) break; // Fin de ligne
        
        // Extraire le token courant (jusqu'au prochain espace ou fin)
        int start = i;
        while (i < ligne.length() && !Character.isWhitespace(ligne.charAt(i))) {
            i++; // Avancer jusqu'à un espace ou fin
        }
        String mot = ligne.substring(start, i);
        
        if (mot.isEmpty()) continue;
        
        // Maintenant, analyser le mot extrait (comme avant)
        String type = checkCommentaire(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        type = checkChaine(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        // ... (ajoute les autres checks comme dans ton code)
        
        type = checkKeyWordOrId(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        
        type = checkOperateur(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        type = checkNombre(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        type = checkAssignation(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        type = checkSeparateur(mot);
        if (type != null) {
            System.out.println(mot + " : " + type);
            continue;
        }
        System.out.println(mot + " : Erreur! Type inconnu.");
    }
}
}