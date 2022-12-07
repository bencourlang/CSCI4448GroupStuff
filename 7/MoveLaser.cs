using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveLaser : MonoBehaviour
{
    private float moveSpeed = 10;

    private int movePosX = 1;
    private int movePosZ = 1;

    //private float delay = 3;

    private int dmgToGive = 1;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        /*if(delay >= 0){
            delay -= Time.deltaTime;
            return;
        }*/
        
        //move x
        if(gameObject.name == "xLaser(Clone)"){
            if(transform.position.x > 20){
                movePosX = -1;
            }
            else if(transform.position.x < -20){
                movePosX = 1;
            }

            transform.position = new Vector3(transform.position.x + (moveSpeed * Time.deltaTime * movePosX), transform.position.y, transform.position.z);
        }

        //move z
        if(gameObject.name == "zLaser(Clone)"){
            if(transform.position.z > 20){
                movePosZ = -1;
            }
            else if(transform.position.z < -20){
                movePosZ = 1;
            }

            transform.position = new Vector3(transform.position.x, transform.position.y, transform.position.z + (moveSpeed * Time.deltaTime * movePosZ));
        }
    }


    private void OnTriggerEnter(Collider other){
        //Debug.Log("I hit: " + other.gameObject.name);

        //FindObjectOfType<HealthManager>().DealDamage(dmgToGive, other);

        if(other.gameObject.tag == "Player"){
            FindObjectOfType<HealthManager>().HurtPlayer(dmgToGive);
        }
        /*if(other.gameObject.tag == "testAI"){
            FindObjectOfType<HealthManager>().HurtAI(dmgToGive);
        }*/
    }
}
