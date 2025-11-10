package Game;

import com.jogamp.opengl.GL2;

public interface GameLoop {

    double PHYSICS_STEP = 1.0 / 500.0; // 60 Hz
    long NANO_TO_SEC = 1_000_000_000L;

    void physicsUpdate();
    void renderUpdate(GL2 gl);

    default void handleLoop(LoopState state, GL2 gl) {
        long now = System.nanoTime();
        double deltaTime = (now - state.lastTime) / 1_000_000_000.0;
        state.lastTime = now;

        state.accumulator += deltaTime;

        while (state.accumulator >= PHYSICS_STEP) {
            physicsUpdate();
            state.accumulator -= PHYSICS_STEP;
        }

        renderUpdate(gl);
    }
}
