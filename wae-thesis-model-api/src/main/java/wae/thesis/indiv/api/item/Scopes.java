package wae.thesis.indiv.api.item;

/**
 * Created by Nguyen Tan Dat.
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return this.name();
    }
}
