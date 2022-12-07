using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TitleScreenAI : MonoBehaviour
{
    /*private int xSpeed;
    private int zSpeed;

    private int xChange;
    private int zChange;

    private float moveDelay = 1f;
    private float cd = 0.5f;

    private int[] index = {-1, 1};*/

    private float jumpForce = 10;
    private float moveSpeed = 10;
    public CharacterController controller;
    private Vector3 moveDirection;

    private float gravityScale = 3;
    private GameObject lastJumped;
    private float lastJumpedcd = 1;

    // Start is called before the first frame update
    void Start()
    {
        controller = GetComponent<CharacterController>();
    }

    // Update is called once per frame
    void Update()
    {
        /*if(moveDelay >= 0){
            moveDelay -= Time.deltaTime;
        }
        else{
            if(cd >= 0){
                move();
                cd -= Time.deltaTime;
            }
            else{
                xSpeed = randNum1To10();
                zSpeed = randNum1To10();

                xChange = changeDir();
                zChange = changeDir();

                moveDelay = 1f;
                cd = 0.5f;
            }
        }*/

        if(lastJumpedcd >= 0){
            lastJumpedcd -= Time.deltaTime;
        }
        else{
            lastJumped = null;
        }
        

        //move();

        float yStore = moveDirection.y;
        //moveDirection = (transform.forward * Input.GetAxis("Vertical") * moveSpeed) + (transform.right * Input.GetAxis("Horizontal") * moveSpeed);
        moveDirection = moveDirection.normalized * moveSpeed;
        moveDirection.y = yStore;

        if(controller.isGrounded){
            moveDirection.y = -3f;

            if(aiJump("Laser")){
                moveDirection.y = jumpForce;
            }
        }

        moveDirection.y += (Physics.gravity.y * Time.deltaTime * gravityScale);
        controller.Move(moveDirection * Time.deltaTime);
    }

    /*void move(){
        if(transform.position.x + (xSpeed * Time.deltaTime * xChange) > 20 || transform.position.x + (xSpeed * Time.deltaTime * xChange) < -20){
            xChange *= -1;
        }
        if(transform.position.z + (zSpeed * Time.deltaTime * zChange) > 20 || transform.position.z + (zSpeed * Time.deltaTime * zChange) < -20){
            zChange *= -1;
        }
        transform.position = new Vector3(transform.position.x + (xSpeed * Time.deltaTime * xChange), transform.position.y, transform.position.z + (zSpeed * Time.deltaTime * zChange));
    }*/

    /*void move(){
        float yStore = moveDirection.y;

        /*if((transform.forward * xSpeed * xChange) + (transform.right * zSpeed * zChange) > 20 || (transform.forward * xSpeed * xChange) + (transform.right * zSpeed * zChange) < -20){
            xChange *= -1;
            zChange *= -1;
        }//here is end comment

        moveDirection = (transform.forward * xSpeed * xChange) + (transform.right * zSpeed * zChange);
        moveDirection = moveDirection.normalized * moveSpeed;
        moveDirection.y = yStore;
    }*/

    /*int randNum1To10(){
        return Random.Range(1, 10);
    }
    int changeDir(){
        return index[Random.Range(0, index.Length)];
    }*/

    bool aiJump(string tag){
        var objectsWithTag = GameObject.FindGameObjectsWithTag(tag);

        foreach(GameObject obj in objectsWithTag){
            if(Vector3.Distance(transform.position, obj.transform.position) <= 3 && obj != lastJumped){
                lastJumped = obj;
                return true;
            }
        }
        return false;
    }
}
