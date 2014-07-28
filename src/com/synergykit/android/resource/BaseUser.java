package com.synergykit.android.resource;

import com.synergykit.android.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas_000 on 27.2.14.
 */
public class BaseUser extends BaseResponse {
    private List<Platform> platforms;

    public BaseUser(String registrationId) {
        Platform platform = new Platform();
        platforms = new ArrayList<Platform>();
        platform.setRegistrationId(registrationId);
        platform.setName("android");
        platforms.add(platform);
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public static class Platform {
        private String name;
        private String registrationId;
        private Boolean development;

        public Boolean getDevelopment() {
            return development;
        }

        public void setDevelopment(Boolean development) {
            this.development = development;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegistrationId() {
            return registrationId;
        }

        public void setRegistrationId(String registrationId) {
            this.registrationId = registrationId;
        }
    }

}
