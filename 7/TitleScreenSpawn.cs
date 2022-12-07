using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TitleScreenSpawn : MonoBehaviour
{
    public GameObject AI;

    public GameObject xLaser;
    public GameObject zLaser;
    
    // Start is called before the first frame update
    void Start()
    {
        Instantiate(AI, new Vector3(0f, 1f, 0f), Quaternion.identity);
        spawnXLaser();
        //spawnZLaser();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void spawnZLaser(){
        Instantiate(zLaser, new Vector3(0f, 0.5f, 20f), Quaternion.identity);
    }

    void spawnXLaser(){
        Instantiate(xLaser, new Vector3(-20f, 0.5f, 0f), transform.rotation * Quaternion.Euler (0f, 90f, 0f));
    }
}
