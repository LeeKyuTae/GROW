package grow.demo.routine.domain.routine;

import java.util.Random;

public class RoutineImage {

    public static final String[] maleImage ={
            /*
            "https://images.unsplash.com/photo-1549060279-7e168fcee0c2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80 750w",
            "https://images.unsplash.com/photo-1507398941214-572c25f4b1dc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=666&q=80 666w",
            "https://images.unsplash.com/photo-1532384816664-01b8b7238c8d?ixlib=rb-1.2.1&amp;auto=format&amp;fit=crop&amp;w=334&amp;q=80 334w",
            "https://images.unsplash.com/photo-1530822847156-5df684ec5ee1?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1556817411-58c45dd94e8c?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1526314149856-d8cf115d62f1?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=387&amp;q=80 387w",
            "https://images.unsplash.com/photo-1532383391693-13c3833a7de6?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1549481446-baf395392d13?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=334&amp;q=80 334w",


             */
            "https://storage.googleapis.com/routine_image/man1.jpg",
            "https://storage.googleapis.com/routine_image/man2.jpg",
            "https://storage.googleapis.com/routine_image/man3.jpg",
            "https://storage.googleapis.com/routine_image/man4.jpg",
            "https://storage.googleapis.com/routine_image/man5.jpg",
            "https://storage.googleapis.com/routine_image/man6.jpg",
            "https://storage.googleapis.com/routine_image/man7.jpg",
            "https://storage.googleapis.com/routine_image/man8.jpg",
            "https://storage.googleapis.com/routine_image/man9.jpg",
            "https://storage.googleapis.com/routine_image/man10.jpg",
            "https://storage.googleapis.com/routine_image/man11.jpg",
            "https://storage.googleapis.com/routine_image/man12.jpg",
            "https://storage.googleapis.com/routine_image/man13.jpg",
            "https://storage.googleapis.com/routine_image/man14.jpg",
            "https://storage.googleapis.com/routine_image/man15.jpg"
    } ;

    public static final String[] femaleImage ={
            /*
            "https://images.unsplash.com/photo-1526506118085-60ce8714f8c5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80",
            "https://images.unsplash.com/photo-1507761906261-d31a39975ce4?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1552364142-f06dd635a796?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1567598317136-3cd762432241?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=750&amp;q=80 750w",
            "https://images.unsplash.com/photo-1509009082772-cb9797f8adbf?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=420&amp;q=80 420w",
            "https://images.unsplash.com/photo-1527248484677-0e5c9a8ab8a7?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=707&amp;q=80 707w",
            "https://images.unsplash.com/photo-1434608519344-49d77a699e1d?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=753&amp;q=80 753w",
            "https://images.unsplash.com/photo-1546483875-ad9014c88eba?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=674&amp;q=80 674w",
            "https://images.unsplash.com/photo-1573124356790-446c0081d9ff?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=334&amp;q=80 334w",
            "https://images.unsplash.com/photo-1561402811-8cf986c35eec?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=573&amp;q=80 573w",
             */
            "https://storage.googleapis.com/routine_image/girl1.jpg",
            "https://storage.googleapis.com/routine_image/girl2.jpg",
            "https://storage.googleapis.com/routine_image/girl3.jpg",
            "https://storage.googleapis.com/routine_image/girl4.jpg",
            "https://storage.googleapis.com/routine_image/girl5.jpg",
            "https://storage.googleapis.com/routine_image/girl6.jpg",
            "https://storage.googleapis.com/routine_image/girl7.jpg",
            "https://storage.googleapis.com/routine_image/girl8.jpg",
            "https://storage.googleapis.com/routine_image/girl9.jpg",
            "https://storage.googleapis.com/routine_image/girl10.jpg"

    } ;

    public static String getImageUrl(Long Id, String gender){
        Random rand = new Random();
        if(gender == null)
            gender = "male";

        if(gender.equals("female")){
            return femaleImage[rand.nextInt(femaleImage.length)];
        } return maleImage[rand.nextInt(maleImage.length)];
    }
}
