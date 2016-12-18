package nyc.c4q.rusili.catchemall.Database;

public class PokeModel {

    private Long _id;
    private String givenName;
    private String nickName;
    private String type;
    private String image_url1;
    private String image_url2;
    private long timeCaught;

    public PokeModel(Long id, String givenName, String nickName, String type, String image_url1, String image_url2, long timeCaught) {
        this._id = id;
        this.givenName = givenName;
        this.nickName = nickName;
        this.type = type;
        this.image_url1 = image_url1;
        this.image_url2 = image_url2;
        this.timeCaught = timeCaught;
    }
}
