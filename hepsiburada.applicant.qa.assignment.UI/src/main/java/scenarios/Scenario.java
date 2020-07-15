/**
 * 
 */
package scenarios;

import java.util.Map;

/**
 * @author km10188
 *
 */
public interface Scenario {
	
	public  Map<String, Object> beforeRun ();
	
	public void run(Map<String, Object> params);
}
