import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {

        /**
         * This keyboard arrangement imitates a piano keyboard: The “white keys” are on the qwerty and zxcv rows
         * and the “black keys” on the 12345 and asdf rows of the keyboard.
         */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        /** Creates objects for each key in the string keyboard. */
        GuitarString[] gs = new GuitarString[keyboard.length()];

        for (int i = 0; i < gs.length; i += 1) {
            double frequency = 440 * Math.pow(2.0, (i - 24.0) / 12.0);
            gs[i] = new GuitarString(frequency);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    continue;
                } else {
                    gs[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString key : gs) {
                sample += key.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString key : gs) {
                key.tic();
            }
        }
    }
}
