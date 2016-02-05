package CrimeSceneInvestigator;

public class MainController {

    public void controlfaelle() {
        //System.out.println("faelle");
    }

    public static String formatDateToDMY(String date) {
        if (date != null && date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] splitdate = date.split("-");
            return splitdate[2]+"."+splitdate[1]+"."+splitdate[0];
        }
        return date;
    }

    public static String formatDateToYMD(String date) {
        if (date != null && date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            String[] splitdate = date.split("\\.");
            return splitdate[2]+"-"+splitdate[1]+"-"+splitdate[0];
        }
        return date;
    }

}
