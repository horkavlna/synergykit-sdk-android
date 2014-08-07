package com.synergykit.android.resource;

import java.util.ArrayList;
import java.util.List;
/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */
public class BaseUser extends SynergyKITBaseObject {
    /* Attributes */
	private String application;
	private String name;
	private List<Platform> platforms;

	/* Constructor */
    public BaseUser(String registrationId) {
        Platform platform = new Platform();
        platforms = new ArrayList<Platform>();
        platform.setRegistrationId(registrationId);
        platform.setName("android");
        platforms.add(platform);
    }
    

	/* application getter  */
	public String getApplication() {
		return application;
	}

	/* application setter */
	public void setApplication(String application) {
		this.application = application;
	}

	/* name getter */
	public String getName() {
		return name;
	}

	/* name setter */
	public void setName(String name) {
		this.name = name;
	}

	/* Platforms getter */
    public List<Platform> getPlatforms() {
        return platforms;
    }

    /*Platforms setter */
    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    /* Static class Platform */
    public static class Platform {
        private String name;
        private String applicationId;
        private String registrationId;
        


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

		public String getApplicationId() {
			return applicationId;
		}

		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
    }

}
