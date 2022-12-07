using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LaserManager : MonoBehaviour
{
    public GameObject xLaser;
    public GameObject zLaser;

    private float delayBar1 = 5;
    private bool spawnedBar1 = false;

    private float delayBar2 = 5;
    private bool spawnedBar2 = false;

    private float delayBar3 = 5;
    private bool spawnedBar3 = false;

    private float delayBar4 = 5;
    private bool spawnedBar4 = false;

    private float delayBar5 = 10;
    private bool spawnedBar5 = false;

    private float delayBar6 = 10;
    private bool spawnedBar6 = false;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        //1
        if(delayBar1 >= 0){
            delayBar1 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar1){
            spawnXLaser();
            spawnedBar1 = true;
        }

        //2
        if(delayBar2 >= 0){
            delayBar2 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar2){
            spawnZLaser();
            spawnedBar2 = true;
        }

        //3
        if(delayBar3 >= 0){
            delayBar3 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar3){
            spawnXLaser();
            spawnedBar3 = true;
        }

        //4
        if(delayBar4 >= 0){
            delayBar4 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar4){
            spawnZLaser();
            spawnedBar4 = true;
        }

        //5
        if(delayBar5 >= 0){
            delayBar5 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar5){
            spawnXLaser();
            spawnedBar5 = true;
        }

        //6
        if(delayBar6 >= 0){
            delayBar6 -= Time.deltaTime;
            return;
        }
        if(!spawnedBar6){
            spawnZLaser();
            spawnedBar6 = true;
        }
    }

    void spawnZLaser(){
        Instantiate(zLaser, new Vector3(0f, 0.5f, 20f), Quaternion.identity);
    }

    void spawnXLaser(){
        Instantiate(xLaser, new Vector3(-20f, 0.5f, 0f), transform.rotation * Quaternion.Euler (0f, 90f, 0f));
    }
}
