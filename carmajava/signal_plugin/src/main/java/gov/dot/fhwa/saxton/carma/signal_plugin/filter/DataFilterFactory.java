/*
 * Copyright (C) 2018 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package gov.dot.fhwa.saxton.carma.signal_plugin.filter;


/**
 * Creates a concrete ISmoother class based on the provided class name
 * 
 * @author starkj
 *
 */
public class DataFilterFactory {

	public static IDataFilter newInstance(String className) {
		@SuppressWarnings("rawtypes")
		Class tClass = null;
		
        try   {
            tClass = Class.forName(className);
        }
        catch(ClassNotFoundException cnfe)   {
            tClass = NoFilter.class;
        }

        Object newObject = null;
        try   {
            newObject = tClass.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (IDataFilter)newObject;
	}
}
