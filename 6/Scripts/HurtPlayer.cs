using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HurtPlayer : MonoBehaviour
{
    //Only attatched to lava and does nothing right now

    public int dmgToGive = 1;
    // Start is called before the first frame update
    void Start()
    {
        if(gameObject.name == "Lava"){
            dmgToGive = 3;
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other){
        //FindObjectOfType<HealthManager>().DealDamage(dmgToGive, other);
        
        if(other.gameObject.name == "Player"){
            FindObjectOfType<HealthManager>().HurtPlayer(dmgToGive);
        }
        /*if(other.gameObject.tag == "AI"){
            FindObjectOfType<HealthManager>().HurtAI(dmgToGive);
        }*/
    }
}
