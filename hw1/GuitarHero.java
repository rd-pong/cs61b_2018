/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
//        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
//        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        // create array
        synthesizer.GuitarString[] stringArray = new synthesizer.GuitarString[37];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = new synthesizer.GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                stringArray[keyboard.indexOf(key)].pluck();
            }

            /* compute the superposition of samples */
            //double sample = stringA.sample() + stringC.sample();

            double sample = 0;
            for (int i = 0; i < stringArray.length; i++) {
                sample += stringArray[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i].tic();
            }
        }
    }
}

