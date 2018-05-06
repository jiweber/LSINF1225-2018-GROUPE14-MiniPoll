package be.lsinf1225.minipoll;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Classe générale de l'application.
 * <p>
 * Cette classe permet d'obtenir facilement le contexte de l'application. Le contexte de
 * l'application est particulièrement utile dans cette application pour le MySQLiteHelper afin de
 * communiquer avec la base de données.
 * <p>
 * Elle est également utilisée pour effectuer des petites notifications en une ligne de code grâce
 * aux méthodes notifyShort et notifyLong.
 *
 * @author Damien Mercier
 * @version 1
 * @note Pour que cette classe soit utilisée par Android, il faut la déclarer dans
 * l'AndroidManifest.xml : <application android:name="be.uclouvain.lsinf1225.collector.MusicPlayerApp"
 * (...)>(...)</application>
 */

public class MiniPoll extends Application {

    private static String userMail;

    public static String getUserMail() {
        return userMail;
    }
    public static void setUserMail(String userMail) {
        userMail = userMail;
    }

    /**
     * Référence au contexte de l'application
     */
    private static MiniPoll context;

    /**
     * Fournit le contexte de l'application.
     *
     * @return Contexte de l'application.
     */
    public static MiniPoll getContext() {
        return context;
    }

    /**
     * Affiche une notification pendant une courte durée à l'utilisateur.
     *
     * @param resId Id de la ressource (R.string.* ) contenant le message à afficher.
     *
     * @see MiniPoll#notify
     */
    public static void notifyShort(int resId) {
        notify(resId, Toast.LENGTH_SHORT);
    }

    /**
     * Affiche une notification pendant une longue durée à l'utilisateur.
     *
     * @param resId Id de la ressource (R.string.* ) contenant le message à afficher.
     *
     * @see MiniPoll#notify
     */
    public static void notifyLong(int resId) {
        notify(resId, Toast.LENGTH_LONG);
    }

    /**
     * Affiche une notification à l'utilisateur. Cette notification est centrée sur l'écran afin
     * qu'elle soit bien visible même lorsque le clavier est actif.
     *
     * @param resId    Id de la ressource (R.string.* ) contenant le message à afficher.
     * @param duration Durée d'affichage (Toast.LENGTH_SHORT ou Toast.LENGTH_LONG)
     */
    private static void notify(int resId, int duration) {
        Toast msg = Toast.makeText(getContext(), getContext().getString(resId), duration);
        msg.setGravity(Gravity.CENTER, 0, 0);
        msg.show();
    }

    public void onCreate() {
        super.onCreate();
        context = (MiniPoll) getApplicationContext();
    }
}
