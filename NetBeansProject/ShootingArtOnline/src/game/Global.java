/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author snake00
 */
public class Global {

	public static int mouseX;
	public static int mouseY;
	public static int myX;
	public static int myY;
	public static int enemyX;
	public static int enemyY;
	public static int myBulletNum;
	public static boolean isMyBulletFire;
	public static boolean isBoostUse;
	public static boolean isNPC;
	public static int enemyBulletNum;
	public static boolean isMacth;
	public static boolean isMousePressed;
	public static boolean isHardMode;

	/**
	 * enemy bullet
	 */
	public static Bullet[] enemyBullet;
	/**
	 * my bullet
	 */
	public static Bullet[] playerBullet;

	/**
	 * frame count
	 */
	public static int FrameCount;
	

	/**
	 * mouse set X
	 *
	 * @param x
	 */
	public static void setMouseX(double x) {
		mouseX = (int) x;
//		System.out.println("setok");
	}

	/**
	 * mouse get X
	 *
	 * @return
	 */
	public static int getMouseX() {
		return mouseX;
	}

	/**
	 * my machine getX
	 *
	 * @return
	 */
	public static int getX() {
		return myX;
	}

	/**
	 * my machine set X
	 *
	 * @param myx
	 */
	public static void setX(int myx) {
		myX = myx;

	}

	/**
	 * my machine get Y
	 *
	 * @return
	 */
	public static int getY() {
		return myY;
	}

	/**
	 * my machine set Y
	 *
	 * @param y
	 */
	public static void setMouseY(double y) {
		mouseY = (int) y;
	}

	/**
	 * get mouse Y
	 *
	 * @return
	 */
	public static int getMouseY() {
		return mouseY;
	}

	/**
	 * my machine get Y
	 *
	 * @param myy
	 */
	public static void setY(int myy) {
		myY = myy;
	}

	/**
	 * set enemy X
	 *
	 * @param enemyx
	 */
	public static void setEnemyX(int enemyx) {
		//System.out.println("enemyX" + enemyx);
		enemyX = enemyx;
	}

	/**
	 * get enemy X
	 *
	 * @return
	 */
	public static int getEnemyX() {
		return enemyX;
	}

	/**
	 * set enemy Y
	 *
	 * @param enemyy
	 */
	public static void setEnemyY(int enemyy) {
//		System.out.println("enemyY" + enemyy);
		enemyY = enemyy;
	}

	/**
	 * get enemy Y
	 *
	 * @return
	 */
	public static int getEnemyY() {
		return enemyY;
	}

	/**
	 * my bullet number get
	 *
	 * @return
	 */
	public static int getMyBulletNum() {
		return myBulletNum;
	}

	/**
	 * my bullet number set
	 *
	 * @param num
	 */
	public static void setMyBulletNum(int num) {
		myBulletNum = num;
	}
	
	/**
	 * enemy bullet number get
	 *
	 * @return
	 */
	public static int getEnemyBulletNum() {
		return enemyBulletNum;
	}

	/**
	 * enemy bullet number set
	 *
	 * @param num
	 */
	public static void setEnemyBulletNum(int num) {
		enemyBulletNum = num;
	}

	/**
	 * get my bullet fire
	 *
	 * @return
	 */
	public static boolean getMyBulletFire() {
		return isMyBulletFire;
	}

	/**
	 * set my bullet fire
	 *
	 * @param isFire
	 */
	public static void setMyBulletFire(boolean isFire) {
		isMyBulletFire = isFire;
	}

	/**
	 * get boost use true or false
	 *
	 * @return
	 */
	public static boolean getBoostUse() {
		return isBoostUse;
	}

	public static void setBoostUse(boolean isUse) {
		isBoostUse = isUse;
	}

	/**
	 * set enemy is NPC or player
	 * @param npc
	 */
	public static void setNPC(boolean npc) {
		isNPC = npc;
	}

	/**
	 * get enemy is NPC or player
	 * @return 
	 */
	public static boolean getNPC() {
		return isNPC;
	}

	public static void setMatch(boolean match){
		isMacth = match;
	}

	public static boolean getMatch(){
		return isMacth;
	}

	/**
	 * @return the getMousePressed
	 */
	public static boolean getMousePressed() {
		return isMousePressed;
	}

	/**
	 * @param aIsMousePressed the isMousePressed to set
	 */
	public static void setMousePressed(boolean aIsMousePressed) {
		isMousePressed = aIsMousePressed;
	}
	
	public static boolean  getHardMode(){
		return isHardMode;
	}
	public static void setHardMode(boolean b) {
		isHardMode = b;
	}
}
