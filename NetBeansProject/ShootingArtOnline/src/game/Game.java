/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import config.GameConfig;
import static config.GameConfig.EnemyBulletCount;
import static config.GameConfig.PlayerBulletCount;
import static game.Global.FrameCount;
import static game.Global.enemyBullet;
import static game.Global.getEnemyX;
import static game.Global.getEnemyY;
import static game.Global.getX;
import static game.Global.getY;
import static game.Global.playerBullet;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import shootingartonline.SocketInput;

/**
 *
 * @author snake00
 */
public class Game extends Task {

	Player player;
	Enemy enemy;
	Canvas pane;
	Title title;
	boolean GameFlag = true;
	boolean OptionFlag = false;
	boolean SelectFlag = false;
	GraphicsContext gc;
	Timeline timeline;
	private final Font font = new Font("Italic", 80);

	public Game(Canvas pane) {
		this.pane = pane;
		gc = pane.getGraphicsContext2D();

	}

	public void run() {
		bg();
		if (GameFlag) {
			Game();
		}
		/*if (OptionFlag) {
		 option.draw();
		 }*/
		if (SelectFlag) {
			//select.draw();
		}
		if (title.getTitleFlag()) {
			title.draw(gc);
			if (!title.getTitleFlag()) {
				GameFlag = !GameFlag;
				if (title.isVS) {
					Global.setNPC(true);
					FrameCount = 0;
				} else if (title.isCPU) {
					Global.setNPC(false);
					FrameCount = 0;
				}
			}
		}
		FrameCount++;
	}

	public void start(Game game) {

		setUpGame();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {

				gc = pane.getGraphicsContext2D();
				gc.clearRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);
				run();

			}
		};

		timer.start();

	}

	private void setUpGame() {
		FrameCount = 0;

		player = new Player(100, 200);
		enemy = new Enemy(1000, 750);
		title = new Title();
		/**
		 * enemy test
		 */
		Global.setEnemyX(700);
		Global.setEnemyY(400);
		playerBullet = new Bullet[PlayerBulletCount];
		for (int i = 0; i < playerBullet.length; i++) {
			playerBullet[i] = new Bullet();
		}
		enemyBullet = new Bullet[EnemyBulletCount];
		for (int i = 0; i < enemyBullet.length; i++) {
			enemyBullet[i] = new Bullet();
		}
		GameFlag = false;
		title.setTitleFlag(true);
		bg();

		SocketInput input = new SocketInput();
		Thread t1 = new Thread(input);
		t1.start();
	}

	private void bg() {
		gc.setFill(Color.hsb(0, 0, 0, 1.0));
		gc.fillRect(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

	}

	private void Game() {
		if (title.isVS && !Global.getMatch()) {
			gc.setFill(Color.hsb(0, 1, 1, 1));
			gc.setFont(font);
			gc.fillText("Matching....", GameConfig.WIDTH / 2 - 200, GameConfig.HEIGHT / 2);
			FrameCount = 0;
		} else if (title.isCPU && FrameCount <= 60) {
			gc.setFill(Color.hsb(180, 1, 1, 1));
			gc.setFont(font);
			gc.fillText("S A O", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2);

		} else if (title.isCPU && FrameCount > 60 && FrameCount <= 90) {
			gc.setFill(Color.hsb(180, 1, 1, 1));
			gc.setFont(font);
			gc.fillText("S A O", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2);
			gc.fillText("start", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2 + 80);

		} else if (title.isVS && FrameCount <= 60) {
			gc.setFill(Color.hsb(180, 1, 1, 1));
			gc.setFont(font);
			gc.fillText("S A O", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2);

		} else if (title.isVS && FrameCount > 60 && FrameCount <= 90) {
			gc.setFill(Color.hsb(180, 1, 1, 1));
			gc.setFont(font);
			gc.fillText("S A O", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2);
			gc.fillText("start", GameConfig.WIDTH / 2 - 100, GameConfig.HEIGHT / 2 + 80);

		} else {

			player.update();
			enemy.update();
			for (Bullet playerBullet1 : playerBullet) {
				playerBullet1.update();
			}
			for (Bullet enemyBullet1 : enemyBullet) {
				enemyBullet1.update();
			}

			// 自機が弾を撃つ。
			player.fire();
			// 敵が弾を撃つ。
			enemy.fire();

			for (Bullet playerBullet1 : playerBullet) {
				playerBullet1.draw(gc);
			}
			for (Bullet enemyBullet1 : enemyBullet) {
				enemyBullet1.draw(gc);
			}
			player.draw(gc);
			enemy.draw(gc);

			for (int i = 0; i < enemyBullet.length; i++) {
				if (enemyBullet[i].isDead()) {
					continue;
				}

				float dx = (enemyBullet[i].getX() + enemyBullet[i].getRadius() / 2) - (getX() + player.getRadius() / 2);
				float dy = (enemyBullet[i].getY() + enemyBullet[i].getRadius() / 2) - (getY() + player.getRadius() / 2);
				float r = enemyBullet[i].getRadius() / 2 + player.getRadius() / 2;

				if (dx * dx + dy * dy < r * r) {
					player.reduceLife(i);
					enemyBullet[i].kill();
				}
			}

			// 敵と自機の弾との衝突判定。
			for (int i = 0; i < playerBullet.length; i++) {
				if (playerBullet[i].isDead()) {
					continue;
				}

				float dx = (playerBullet[i].getX() + playerBullet[i].getRadius() / 2) - (getEnemyX() + enemy.getRadius() / 2);
				float dy = (playerBullet[i].getY() + playerBullet[i].getRadius() / 2) - (getEnemyY() + enemy.getRadius() / 2);
				float r = playerBullet[i].getRadius() / 2 + enemy.getRadius() / 2;

				if (dx * dx + dy * dy < r * r) {
					playerBullet[i].kill();
					enemy.reduceLife(i);
				}
			}
		}

	}

	@Override
	protected Object call() throws Exception {
		return null;
	}

}
