using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthManager : MonoBehaviour
{
    //Get AI to take dmg and stuff

    public int maxHp;
    public int currentHp;

    public float iLength;
    private float iCounter;

    public Renderer playerRenderer;
    private float flashCounter;
    public float flashLength = 0.1f;

    public GameObject player;

    public GameObject testAI;


    /*private Renderer renderer;
    private Collider character;
    private GameObject agent;*/

    // Start is called before the first frame update
    void Start()
    {
        currentHp = maxHp;
    }

    // Update is called once per frame
    void Update()
    {
        /*agent = character.gameObject;

        if(agent == null){
            return;
        }
        if(iCounter > 0){
            iCounter -= Time.deltaTime;

            flashCounter -= Time.deltaTime;
            if(flashCounter <= 0){
                renderer.enabled = !playerRenderer.enabled;
                flashCounter = flashLength;
            }

            if(iCounter <= 0){
                renderer.enabled = true;
            }
        }*/

        if(player == null){
            return;
        }
        if(iCounter > 0){
            iCounter -= Time.deltaTime;

            flashCounter -= Time.deltaTime;
            if(flashCounter <= 0){
                playerRenderer.enabled = !playerRenderer.enabled;
                flashCounter = flashLength;
            }

            if(iCounter <= 0){
                playerRenderer.enabled = true;
            }
        }

        /*if(testAI == null){
            return;
        }
        if(iCounter > 0){
            iCounter -= Time.deltaTime;

            flashCounter -= Time.deltaTime;
            if(flashCounter <= 0){
                playerRenderer.enabled = !playerRenderer.enabled;
                flashCounter = flashLength;
            }

            if(iCounter <= 0){
                playerRenderer.enabled = true;
            }
        }*/
    }

    /*public void DealDamage(int damage, Collider other){
        character = other;
        if(iCounter <= 0){
            character.gameObject.currentHp -= damage;

            iCounter = iLength;

            if(character.gameObject.currentHp <= 0){
                Destroy(character.gameObject.currentHp);
                return;
            }

            renderer = character.gameObject.GetComponent<Renderer>();
            renderer.enabled = false;
            flashCounter = flashLength;
        }
    }*/

    public void HurtPlayer(int damage){
        if(iCounter <= 0){
            currentHp -= damage;

            iCounter = iLength;

            if(currentHp <= 0){
                Destroy(player);
                return;
            }

            playerRenderer.enabled = false;
            flashCounter = flashLength;
        }
    }

    /*public void HurtAI(int damage){
        if(iCounter <= 0){
            currentHp -= damage;

            iCounter = iLength;

            if(currentHp <= 0){
                Destroy(testAI);
                return;
            }

            playerRenderer.enabled = false;
            flashCounter = flashLength;
        }
    }*/

    public int getLives(){
        return currentHp;
    }
}
