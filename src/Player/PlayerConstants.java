package Player;
//주석 완료
import java.awt.*;

public class PlayerConstants {

    final public static Color[] PLAYER_COLOR = {//플레이어 색
            new Color(212, 45, 38),
            new Color(254, 250, 80),
            new Color(83,169,73),
            new Color(47,116, 165)};

    final public static String[] PLAYER_ICON = {//플레이어 아이콘
            "./image/player1.png",
            "./image/player2.png",
            "./image/player3.png",
            "./image/player4.png",
    };

    final public static String[] PLAYER_UNIT = {//플레이어 말 아이콘
            "./image/player1_30.png",
            "./image/player2_30.png",
            "./image/player3_30.png",
            "./image/player4_30.png",
    };

    final public static String[][] RANKING =
                    {{"순위","플레이어","잔고"},
                    {"1","",""},
                    {"2", "","파산"},
                    {"3", "","파산"},
                    {"4", "","파산"}};
    /*
    RANKING data 넣어야함
     */
}
