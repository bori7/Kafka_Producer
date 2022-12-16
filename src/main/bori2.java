import java.util.ArrayList;

import java.util.List;

public class BuildingsWithWifi {

    /*
     * 
     * Buildings with Wifi - Takes in three lists and finds if all buildings are
     * served -
     * 
     * A building is considered served when the number of routers supporting the
     * building is equal to or greater than the head count of the building
     * 
     * 'buildingCount' is a head count per building (each value represents a
     * building) -
     * 
     * 'routerLocation' is the building number where a router is located (buildings
     * numbered ascending from 1)
     * 
     * 'routerRange' is connected by index to routerLocation and represents the
     * range of the router (location + & - range ---- inclusive)
     * 
     * Return the number of served buildings
     * 
     */

    // Tested in Medium Tests folder

    /*
     * NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~
     * 
     * 
     * 
     * I completed this challenge during an interview and had 10/14 test cases
     * passing with this solution
     * 
     * Test cases that I've thought of and pass can be found in the corresponding
     * test folder
     * 
     * 
     * 
     * If you can figure out what test cases I missed, I would love to hear from you
     * with the case and the solution if you've cracked it :)
     * 
     * maxwell.bsmith0@gmail.com
     * 
     * 
     * 
     * NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~ NOTE ~
     */

    // Create router class for easy reference to location and range

    public static class Router {

        int location;

        int range;

        Router(int location, int range) {

            this.location = location;

            this.range = range;

        }

    }

    public int getServedBuildings(List<Integer> buildingCount, List<Integer> routerLocation,
            List<Integer> routerRange) {

        Integer[] buildingsServedArray = buildingCount.toArray(new Integer[0]);

        List<Router> routers = new ArrayList<>();

        int buildingsServed = 0;

        // Loop and add location and range to routers list - (subtract 1 from location
        // for 0 indexing)

        for (int i = 0; i < routerLocation.size(); i++) {

            routers.add(new Router((routerLocation.get(i) - 1), routerRange.get(i)));

        }

        // Loop over routers

        for (Router i : routers) {

            // Check if router location minus range is in bounds (greater than or equal to
            // 0) - set index

            int index = Math.max(i.location - i.range, 0);

            // While index is in bounds and less than router location plus router range

            while (index < buildingsServedArray.length && index <= i.location + i.range) {

                // Subtract 1 from each building in range

                buildingsServedArray[index] -= 1;

                // Increment index

                index++;

            }

        }

        // Loop buildings served array

        for (int i : buildingsServedArray) {

            // If the building has a value of 0 or less, it has been served

            if (i <= 0) {

                // Count

                buildingsServed++;

            }

        }

        // Return total buildings served

        return buildingsServed;

    }

}
