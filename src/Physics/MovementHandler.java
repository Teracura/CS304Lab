package Physics;

import Game.Input;
import Game.InputHandler;

import java.util.HashMap;
import java.util.Map;

public class MovementHandler {

    private final Map<Input, Action> actionMap = new HashMap<>();
    private final InputHandler inputHandler;

    public MovementHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void bind(Input key, Action action) {
        actionMap.put(key, action);
    }

    public void update() {
        int state = inputHandler.getInputState();

        for (Map.Entry<Input, Action> entry : actionMap.entrySet()) {
            Input key = entry.getKey();
            Action action = entry.getValue();

            if (Input.isSet(state, key)) {
                action.execute();
            }
        }
    }
}
