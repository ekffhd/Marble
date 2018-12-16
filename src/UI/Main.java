package UI;
//주석 완료
import Player.Player;
import Property.GoldCard;
import Util.Dice;
import Util.Phase;
import Util.PhaseListener;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private Phase                   phase;
    private PhaseListener           phaseListener;
    protected static GameBoard      gameBoard;
    private ScoreBoard              scoreBoard;
    private StartPanel              startPanel;
    private GoldCard                goldCard;
    private GameOverPanel           gameoverPanel;
    private Dice                    dice1, dice2;
    private int                     dice1Num, dice2Num;
    protected static Player[]       player;
    protected static int            playerTurn;
    protected static int            originPosition;
    protected static int            nextPosition;
    protected static int            cardId;
    protected int                   afterPosition;
    protected static Player         activePlayer;
    private int                     destination, originalOwner;
    private int                     expense, land, house, building, hotel, landmark; // 선택 여부
    private int                     price;
    private Player[]                rank;
    private int                     dieCount;

    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        //Phase
        phase = new Phase();
        playerTurn = 0;
        originPosition = 0;

        //주사위
        dice1 = new Dice();
        dice2 = new Dice();
        goldCard = new GoldCard();

        //플레이어
        player = new Player[4];
        for(int i=0; i<4; i++){
            player[i] = new Player(i);
            player[i].set_position(0);
            player[i].add_cash(2000000);
        }

        activePlayer = player[0];

        rank = new Player[4];
        dieCount = 0;

        //게임 판
        gameBoard = new GameBoard(phase);
        gameBoard.setVisible(false);
        add(gameBoard);

        //점수 판
        scoreBoard = new ScoreBoard(player);
        scoreBoard.setVisible(false);
        add(scoreBoard);

        //시작 화면 패널
        startPanel = new StartPanel(phase);
        startPanel.setVisible(true);
        add(startPanel);

        //Phase Listener
        phaseListener = new PhaseListener(dice1, dice2, gameBoard, this);
        phase.addPropertyChangeListener(phaseListener);
        phase.before_start();

        //게임 오버 패널
        gameoverPanel = new GameOverPanel(phase);
        gameoverPanel.setVisible(false);
        add(gameoverPanel);
    }

    //현재 턴 플레이어 클래스 반환
    public Player get_active_player(){
        return player[playerTurn%4];
    }
    //active플레이어 설정
    public void set_active_player() {
        this.activePlayer = player[playerTurn % 4];
    }

    public void start(){
        startPanel.setVisible(false);
        gameBoard.setVisible(true);
        scoreBoard.setBorder(0);
        scoreBoard.setVisible(true);
    }

    //턴 넘기기
    public void next(){

        int isLandCount = player[playerTurn%4].get_island_count();

        if(dice1Num == dice2Num && isLandCount == 0 && activePlayer.get_status()){
            //더블이 나왔을 때, 한번 더 주사위를 한번 더 던진다.
            gameBoard.gameControllerPanel.doubleButton.setVisible(true);
        }
        else{
            //더블이 나오지 않았을 경우, 턴을 넘긴다.
            //이번 턴에 주사위를 던진 횟수를 초기화시킨다. (한 턴에 주사위를 4번이상 던질 경우엔 무인도로 이동)
            activePlayer.init_dice_count();

            this.playerTurn++;
            set_active_player();

            while(!activePlayer.get_status()){
                this.playerTurn++;
                set_active_player();
            }

            scoreBoard.setBorder(activePlayer.get_player_id());

            if(dieCount!=3){
                gameBoard.gameControllerPanel.rollButton.setVisible(true);
            }
            else{
                gameBoard.hide_player(activePlayer.get_player_id(),activePlayer.get_position());
                activePlayer.game_over(gameBoard.place);
                gameBoard.gameControllerPanel.endButton.setVisible(true);
            }
        }
    }// next()

    //월급 받기
    public void get_salary(){
        // 시작지점을 지나친 플레이어는 20만원을 지급받는다.
        activePlayer.add_cash(200000);
        // scoreBoard의 잔금을 업데이트 한 후, 시작 지점 패널을 띄운다.
        scoreBoard.set_player_cash_label(playerTurn%4);
        gameBoard.gameControllerPanel.startCardPanel.setVisible(true);
    } // get_salary()

    //주사위 굴리기
    public void roll_dice(){
        //주사위를 굴린 후 gameControllerpanel에 주사위 값을 띄운다.
        gameBoard.gameControllerPanel.rollButton.setVisible(false);
        dice1.roll_dice();
        dice2.roll_dice();
        dice1Num = dice1.get_dice();
        dice2Num = dice2.get_dice();
        gameBoard.show_dice(dice1Num, dice2Num);

        //주사위를 굴린 횟수를 증가시킨다.
        activePlayer.add_dice_count();
        System.out.println(activePlayer.get_dice_count()+"번 던져서 무인도");

        //플레이어가 무인도에 있을 경우 isLandCount는 1이상이다.
        int isLandCount = activePlayer.get_island_count();


        if(isLandCount!=0){ //플레이어가 무인도에 있을 경우
            if(dice1Num == dice2Num){ //더블이 나왔을 경우
                //탈출성공 패널을 띄운 후, 해당 플레이어의 island count를 초기화시킨다.
                gameBoard.gameControllerPanel.escapeSuccessIslandPanel.setVisible(true);
                scoreBoard.escape_island_icon(playerTurn%4);
                activePlayer.escape_island();
            }
            else{ //더블이 나오지 않았을 경우
                //탈출실패 패널을 띄운 후, 해당 플레이어의 island count를 1 감소시킨다.
                player[playerTurn%4].sub_island_count();
                gameBoard.gameControllerPanel.escapeFailIslandPanel.setVisible(true);
                scoreBoard.sub_island_icon_count(activePlayer.get_player_id(),activePlayer.get_island_count());
            }
        }
        else{ //플레이어가 무인도에 있지 않을 경우
            if(dice1Num == dice2Num && activePlayer.get_dice_count() > 2){ //플레이어가 주사위를 4번이상 던지게 될 경우
                //플레이어를 무인도로 이동시킨다.
                System.out.println(activePlayer.get_dice_count()+"번 던져서 무인도");
                activePlayer.init_dice_count();
                gameBoard.gameControllerPanel.doubleButton.setVisible(false);
                activePlayer.add_island_count();
                move_player(6);
            }
            else{//그렇지 않은 경우
                //moveButton을 활성화 시킨다.
                gameBoard.gameControllerPanel.moveButton.setVisible(true);
            }
        }
    } // roll_dice()

    //땅/건물 구매하기
    public void purchase_property() {
        //구매 정보를 받아온다.
        expense = gameBoard.gameControllerPanel.purchasePanel.get_expense();
        land = gameBoard.gameControllerPanel.purchasePanel.get_land();
        house = gameBoard.gameControllerPanel.purchasePanel.get_house();
        building = gameBoard.gameControllerPanel.purchasePanel.get_building();
        hotel = gameBoard.gameControllerPanel.purchasePanel.get_hotel();
        landmark = gameBoard.gameControllerPanel.purchasePanel.get_landmark();

        //가격 측정 후 해당 플레이어의 잔고에서 가격을 뺀다.
        this.expense = expense*10000;
        activePlayer.sub_cash(expense);
        scoreBoard.set_player_cash_label(playerTurn%4);

        //해당 부지의 빌딩정보를 업데이트 한다.
        if (land == 1) {
            gameBoard.place[nextPosition].purchase_land(activePlayer);

            //player 의 부지 소유 배열에 추가한다.
            activePlayer.add_owned_place(nextPosition);
        }
        if (house == 1) { gameBoard.place[nextPosition].purchase_house();}
        if (building == 1) {gameBoard.place[nextPosition].purchase_building();}
        if (hotel == 1) { gameBoard.place[nextPosition].purchase_hotel(); }
        if (landmark == 1) { gameBoard.place[nextPosition].purchase_landmark();}
        gameBoard.place[nextPosition].update_building_status(playerTurn%4);

        //턴을 넘긴다.
        phase.next();
    }//purchase_property()

    //플레이어 이동
    public void move_player(int afterPosition){
        //플레이어의 이동하기 전/후 위치 저장
        originPosition = activePlayer.get_position();
        nextPosition = (afterPosition)%24;
        activePlayer.set_position(nextPosition);

        //gameBoard에서 플레이어를 이동
        gameBoard.show_hide_player(playerTurn%4, originPosition, nextPosition);


        if(nextPosition < originPosition) {
            // 한 바퀴 돌았을 때 월급지급
            get_salary();
        }
        else{
            //플레이어의 위치에 해당하는 패널을 보여준다.
            phase.gap();
            phase.show_panel();
        }
    }// move_player()

    //황금알 효과
    public void fire_gold_card_effect(){
        switch(cardId){
            case 0:
                // ATM에 30000원 기부
                goldCard.donate(30000, gameBoard.place[12], activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());

                //player 의 소지금이 마이너스가 될 경우, player_dead()를 실행시키고 그렇지 않을 경우, next phase 로 넘어간다.
                if(activePlayer.get_cash()<0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 1:
                // ATM에 50000원 기부
                goldCard.donate(50000, gameBoard.place[12], activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                if(activePlayer.get_cash()<0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 2:
                // ATM에 80000원 기부
                goldCard.donate(80000, gameBoard.place[12], activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                if(activePlayer.get_cash() < 0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 3:
                //면제권
                player[playerTurn%4].set_exemption();
                phase.next();
                break;
            case 4:
                //면제권
                player[playerTurn%4].set_exemption();
                phase.next();
                break;
            case 5:
                //면제권
                player[playerTurn%4].set_exemption();
                phase.next();
                break;
            case 6:
                // 30000원 당첨
                goldCard.lotto(30000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                phase.next();
                break;
            case 7:
                //50000원 당첨
                goldCard.lotto(50000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                phase.next();
                break;
            case 8:
                //80000원 당첨
                goldCard.lotto(80000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                phase.next();
                break;
            case 9:
                //100000원 당첨
                goldCard.lotto(100000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                phase.next();
                break;
            case 10:
                // 이노베이션 센터로 이동
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[23],23, activePlayer);
                originPosition = nextPosition;
                nextPosition = 23;
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 11:
                //세금 30000원 지불
                goldCard.pay_taxes(30000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                if(activePlayer.get_cash() < 0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 12:
                //세금 50000원 지불
                goldCard.pay_taxes(50000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                if(activePlayer.get_cash() < 0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 13:
                //세금 100000원 지불
                goldCard.pay_taxes(100000, activePlayer);
                scoreBoard.set_player_cash_label(activePlayer.get_player_id());
                if(activePlayer.get_cash() < 0){
                    player_dead();
                }
                else{
                    phase.next();
                }
                break;
            case 14:
                if( activePlayer.get_dice_count() >= 2){//한 턴에 3번 이상 주사위를 굴리게 될 경우
                    //지그재그로 이동
                    originPosition = nextPosition;
                    nextPosition = 6;
                    activePlayer.set_position(6);
                    goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                    show_panel();
                }
                else{//아닐 경우
                    //주사위 한번 더 굴리기
                    gameBoard.gameControllerPanel.rollButton.setVisible(true);
                }
                break;
            case 15:
                if( activePlayer.get_dice_count() >= 2){//한 턴에 3번 이상 주사위를 굴리게 될 경우
                    //지그재그로 이동
                    originPosition = nextPosition;
                    nextPosition = 6;
                    activePlayer.set_position(6);
                    goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                    show_panel();
                }
                else{//아닐 경우
                    //주사위 한번 더 굴리기
                    gameBoard.gameControllerPanel.rollButton.setVisible(true);
                }
                break;
            case 16:
                if( activePlayer.get_dice_count() >= 2){//한 턴에 3번 이상 주사위를 굴리게 될 경우
                    //지그재그로 이동
                    originPosition = nextPosition;
                    nextPosition = 6;
                    activePlayer.set_position(6);
                    goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                    show_panel();
                }
                else{//아닐 경우
                    //주사위 한번 더 굴리기
                    gameBoard.gameControllerPanel.rollButton.setVisible(true);
                }
                break;
            case 17:
                //지그재그로 이동
                originPosition = nextPosition;
                nextPosition = 6;
                activePlayer.set_position(6);
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                show_panel();
                break;
            case 18:
                //지그재그로 이동
                originPosition = nextPosition;
                nextPosition = 6;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(6);
                show_panel();
                break;
            case 19:
                //한 칸 뒤로 이동
                afterPosition = nextPosition-1;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                originPosition = nextPosition;
                nextPosition = afterPosition;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 20:
                //세 칸 뒤로 이동
                afterPosition = nextPosition-3;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                originPosition = nextPosition;
                nextPosition = afterPosition;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 21:
                // 두 칸 앞으로 이동
                afterPosition = nextPosition+2;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                originPosition = nextPosition;
                nextPosition = afterPosition;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 22:
                //세 칸 앞으로 이동
                afterPosition = nextPosition+3;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                originPosition = nextPosition;
                nextPosition = afterPosition;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 23:
                //헬기로 이동
                originPosition = nextPosition;
                nextPosition = 18;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
            case 24:
                //헬기로 이동
                originPosition = nextPosition;
                nextPosition = 18;
                goldCard.move_player(gameBoard.place[originPosition], gameBoard.place[nextPosition],nextPosition, activePlayer);
                activePlayer.set_position(nextPosition);
                show_panel();
                break;
        }
    }// fire_gold_card_effect()

    //특수 이벤트 발생
    public void special_event(){

        if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){//황금 오리
            //카드ID를 받은 후, 황금 알 효과 발생
            this.cardId = gameBoard.gameControllerPanel.goldCardPanel.cardId;
            fire_gold_card_effect();
        }
        else if(nextPosition == 6){ //직잭
            //플레이어의 island count를 2로 설정한 후 다음 턴으로 넘어간다.
            activePlayer.add_island_count();
            scoreBoard.set_island_icon_count(playerTurn%4);
            phase.next();
        }
        else if (nextPosition == 12) { //ATM
            price = gameBoard.place[12].get_city_price();
            player[playerTurn%4].add_cash(price);
            gameBoard.place[12].init_price(0);
            scoreBoard.set_player_cash_label(playerTurn%4);
            phase.next();
        }
        else if (nextPosition == 18) { //헬기
            //목적지를 설정한 후, 플레이어를 이동시킨다.
            destination = gameBoard.gameControllerPanel.helicopterPanel.get_destination();
            move_player(destination);
        } // if ~ else if
    }//special_event()

    //돈 지불
    public void bill(int bill){
        //땅의 주인 자금 증가
        player[gameBoard.place[nextPosition].get_land_owner_id()].add_cash(bill);
        scoreBoard.set_player_cash_label(gameBoard.place[nextPosition].get_land_owner_id());

        //땅을 밟은 플레이어 돈 지불
        activePlayer.sub_cash(bill);
        if(activePlayer.get_cash()<0){//파산했을 경우
           player_dead();
        }
        else{
            scoreBoard.set_player_cash_label(activePlayer.get_player_id());

            if(!gameBoard.place[nextPosition].get_landmark_ownership()) { // 매입 가능
                gameBoard.gameControllerPanel.takeOverButton.setVisible(true);
            }
            else{
                phase.next();
            }
        }
        //phase.takeOver();
        //phase.next();
    } // bill()

    public void player_dead(){
        //player 의 상태를 false(파산)으로 바꾼 후, player 의 scoreBoard 배경색을 회색으로 바꾼 후 next phase 로 넘어간다.
        activePlayer.set_status(false);
        scoreBoard.set_player_die(activePlayer.get_player_id());
        scoreBoard.set_player_cash_label(activePlayer.get_player_id());
        gameBoard.hide_player(activePlayer.get_player_id(),nextPosition);
        activePlayer.game_over(gameBoard.place);
        rank[dieCount] = activePlayer;
        dieCount++;
        phase.next();
    } // player_dead()

    //인수
    public void take_over() {
        //인수 정보 설정
        originalOwner = gameBoard.place[nextPosition].get_land_owner_id();
        expense = gameBoard.gameControllerPanel.takeOverPanel.get_expense();
        house = gameBoard.gameControllerPanel.takeOverPanel.get_house();
        building = gameBoard.gameControllerPanel.takeOverPanel.get_building();
        hotel = gameBoard.gameControllerPanel.takeOverPanel.get_hotel();

        //금액 지불
        this.expense = expense * 10000;
        activePlayer.sub_cash(expense);
        scoreBoard.set_player_cash_label(playerTurn%4);
        activePlayer.add_owned_place(nextPosition);

        //땅 판매자 자금 증가
        player[originalOwner].add_cash(expense);
        scoreBoard.set_player_cash_label(originalOwner);
        player[originalOwner].sub_owned_place(nextPosition);

        //땅 정보 및 UI 설정
        gameBoard.place[nextPosition].purchase_land(player[playerTurn%4]);
        if (house == 1) {
            gameBoard.place[nextPosition].purchase_house();
        }
        if (building == 1) {gameBoard.place[nextPosition].purchase_building();}
        if (hotel == 1) { gameBoard.place[nextPosition].purchase_hotel();}
        gameBoard.place[nextPosition].update_building_status(playerTurn%4);

        phase.next();
    } // take_over()

    //도착한 땅에 해당하는 패널 띄우기
    public void show_panel() {
        if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){ //황금오리
            //황금알 버튼 띄우기
            gameBoard.gameControllerPanel.eggButton.setVisible(true);
        }
        else if(nextPosition == 0){//시작지점
            //시작 지점 패널 띄우기
            gameBoard.gameControllerPanel.startCardPanel.setVisible(true);
        }
        else if(nextPosition == 6){//지그재그
            //지그재그 패널 띄우기
            gameBoard.gameControllerPanel.islandPanel.setVisible(true);
        }
        else if(nextPosition == 12){//ATM
            //ATM패널 띄우기
            gameBoard.gameControllerPanel.welfareFacilityPanel.set_price_info();
            gameBoard.gameControllerPanel.welfareFacilityPanel.setVisible(true);
        }
        else if(nextPosition == 18){//헬리콥터
            //헬리콥터 패널 정보 설정 및 띄우기
            gameBoard.gameControllerPanel.helicopterPanel.reset_helicopter_panel();
            gameBoard.gameControllerPanel.helicopterPanel.setVisible(true);
            gameBoard.show_city_number();
        }
        else{//그 외의 땅
            if(gameBoard.place[nextPosition].get_land_owner_id() == -1){//소유자가 없는 땅에 도착했을 경우
                //구매 버튼을 띄운다.
                gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
            }
            else{ //소유자가 있는 땅에 도착했을 경우
                if(gameBoard.place[nextPosition].get_land_owner_id() == playerTurn%4){//자신의 땅에 도착했을 경우
                    if(gameBoard.place[nextPosition].get_landmark_ownership()){//모든 건물을 다 구입했을 경우
                        //바로 다음턴으로 넘어간다.
                        phase.next();
                    }
                    else{
                        //구매 버튼을 띄운다.
                        gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
                    }
                }
                else{//타인의 땅에 도착했을 경우
                    if(player[playerTurn%4].get_exemption() == 1){ //면제권이 있을 경우
                        //TODO 면제 패널
                        player[playerTurn%4].init_exemption();
                        phase.next();
                    }
                    else{//면제권이 없을 경우
                        //통행료 불 버튼을 띄운다.
                        gameBoard.gameControllerPanel.payButton.setVisible(true);
                    }
                }
            }
        }
    } // show_panel

    public void end() {
        rank[3] = activePlayer;
        gameBoard.setVisible(false);
        scoreBoard.setVisible(false);
        gameoverPanel.set_rank(rank);
        gameoverPanel.setVisible(true);
    } // end()

    public void init_main() {
        playerTurn = 0;
        originPosition = 0;

        //플레이어
        for(int i=0; i<4; i++){
            player[i].init_player();
            player[i].set_position(0);
            player[i].add_cash(200000);
        }

        activePlayer = player[0];

        dieCount = 0;

        //점수 판
        for (int i=0 ; i<4; i++) {
            scoreBoard.set_player_cash_label(i);
            scoreBoard.set_player_alive(i);
            gameBoard.place[0].show_player(i);
        }

        gameBoard.gameControllerPanel.endButton.setVisible(false);
        gameBoard.gameControllerPanel.rollButton.setVisible(true);
    } // init_main()
}
